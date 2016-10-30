package cz.muni.fi.pv256.movio2.uco_410371.network;

import android.app.Activity;
import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.BuildConfig;
import cz.muni.fi.pv256.movio2.uco_410371.MainFragment;
import cz.muni.fi.pv256.movio2.uco_410371.R;
import cz.muni.fi.pv256.movio2.uco_410371.interfaces.RetrofitInterface;
import cz.muni.fi.pv256.movio2.uco_410371.models.Movie;
import cz.muni.fi.pv256.movio2.uco_410371.models.MoviesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DownloadService extends IntentService {

    public static final String TAG = DownloadService.class.getName();
    public static final String RESULT_CODE = "resultCode";
    public static final String RESULT_VALUE = "resultValue";

    private static final String MOVIE_DB_API_KEY = BuildConfig.MOVIE_DB_API_KEY;
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final int DOWNLOAD_NOTIFICATION_ID = 100;
    private static final int DONE_NOTIFICATION_ID = 101;
    private static final int ERROR_NOTIFICATION_ID = 102;

    private NotificationManager mNotificationManager;

    public DownloadService() {
        super("download_service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(DOWNLOAD_NOTIFICATION_ID, getDownloadNotification().build());
        initialize();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        mNotificationManager.cancel(ERROR_NOTIFICATION_ID);
        mNotificationManager.cancel(DOWNLOAD_NOTIFICATION_ID);
        mNotificationManager.cancel(DONE_NOTIFICATION_ID);
    }

    private void initialize() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(LoganSquareConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        requestCall(retrofitInterface.getMovies("upcoming", MOVIE_DB_API_KEY), "Upcoming Movies");
        requestCall(retrofitInterface.getMovies("popular", MOVIE_DB_API_KEY), "Popular Movies");
        requestCall(retrofitInterface.getMovies("top_rated", MOVIE_DB_API_KEY), "Top Rated Movies");
        requestCall(retrofitInterface.getMovies("now_playing", MOVIE_DB_API_KEY), "Now Playing Movies");

    }

    private void requestCall(Call<MoviesResponse> moviesResponse, final String type) {
        moviesResponse.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    onDownloadComplete(response.body().getMovies(), type);
                } else {
                    Log.e(TAG, "onResponse: code = " + response.code());
                    switch (response.code()) {
                        case 401: {
                            mNotificationManager.notify(ERROR_NOTIFICATION_ID, getErrorNotification("Invalid API key").build());
                            break;
                        }
                        case 404: {
                            mNotificationManager.notify(ERROR_NOTIFICATION_ID, getErrorNotification("Resource not found").build());
                            break;
                        }
                        default: {
                            mNotificationManager.notify(ERROR_NOTIFICATION_ID, getErrorNotification(String.valueOf(response.code())).build());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
                mNotificationManager.notify(ERROR_NOTIFICATION_ID, getErrorNotification(t.getMessage()).build());
            }
        });
    }

    private void onDownloadComplete(List<Movie> movies, String type) {
        List<Object> list = new ArrayList<>();
        list.add(type);
        list.add(movies);
        Singleton.getInstance().getHorizontalRecyclerViewAdapter().addItems(list);

        mNotificationManager.cancel(DOWNLOAD_NOTIFICATION_ID);
        mNotificationManager.notify(DONE_NOTIFICATION_ID, getDoneNotification().build());
        sendIntent();
    }

    private void sendIntent(){
        Intent intent = new Intent(MainFragment.MESSAGE);
        intent.putExtra(RESULT_CODE, Activity.RESULT_OK);
        intent.putExtra(RESULT_VALUE, true); // if true data pridané do singletonu
        LocalBroadcastManager.getInstance(DownloadService.this).sendBroadcast(intent);
    }

    private NotificationCompat.Builder getDownloadNotification() {
        return new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_movie_download)
                .setContentTitle("Movies Downloading")
                .setContentText("Download in progress")
                .setProgress(0,0,true)
                .setAutoCancel(true);
    }

    private NotificationCompat.Builder getDoneNotification() {
        return new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_movie_done)
                .setContentTitle("Movies Downloading")
                .setContentText("Complete")
                .setAutoCancel(true);
    }

    private NotificationCompat.Builder getErrorNotification(String message) {
        return new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle("Error")
                .setContentText(message)
                .setAutoCancel(true);
    }
}
