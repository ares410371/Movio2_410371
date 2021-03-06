package cz.muni.fi.pv256.movio2.uco_410371.movies.network;

import android.app.Activity;
import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.BuildConfig;
import cz.muni.fi.pv256.movio2.uco_410371.R;
import cz.muni.fi.pv256.movio2.uco_410371.models.Movie;
import cz.muni.fi.pv256.movio2.uco_410371.models.MoviesResponse;
import cz.muni.fi.pv256.movio2.uco_410371.movies.MoviesFragment;
import cz.muni.fi.pv256.movio2.uco_410371.movies.MoviesPresenter;
import cz.muni.fi.pv256.movio2.uco_410371.util.DateUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DownloadService extends IntentService {

    public static final String TAG = DownloadService.class.getName();
    public static final String RESULT_CODE = "resultCode";
    public static final String RESULT_TYPE = "resultType";
    public static final String RESULT_MOVIES = "resultMovies";

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final int DOWNLOAD_NOTIFICATION_ID = 100;
    private static final int DONE_NOTIFICATION_ID = 101;
    private static final int ERROR_NOTIFICATION_ID = 102;

    private NotificationManager mNotificationManager;
    private MoviesApi mMoviesApi;

    public DownloadService() {
        super("download_service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(DOWNLOAD_NOTIFICATION_ID, getDownloadNotification().build());
        init();

        String category = intent.getStringExtra(MoviesPresenter.CATEGORY);

        switch (category) {
            case "upcoming": {
                requestCall(getMovies(null, DateUtils.getFormattedDay(3), DateUtils.getFormattedDay(25)), getString(R.string.upcoming_movies_cat));
                break;
            }
            case "nowplaying": {
                requestCall(getMovies(null, DateUtils.getFormattedDay(-25), DateUtils.getFormattedDay(3)), getString(R.string.now_playing_movies_cat));
                break;
            }
            case "popular": {
                requestCall(getMovies("popularity.desc", null, null), getString(R.string.popular_movies_cat));
                break;
            }
        }
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        mNotificationManager.cancel(ERROR_NOTIFICATION_ID);
        mNotificationManager.cancel(DOWNLOAD_NOTIFICATION_ID);
        mNotificationManager.cancel(DONE_NOTIFICATION_ID);
    }

    //*************************
    //*****Private Methods*****

    private void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(LoganSquareConverterFactory.create())
                .build();

        mMoviesApi = retrofit.create(MoviesApi.class);
    }

    private Call<MoviesResponse> getMovies(@Nullable String sortBy, @Nullable String minimumDate, @Nullable String maximumDate) {
        return mMoviesApi.getDiscoverMovies(
                BuildConfig.MOVIE_DB_API_KEY,
                sortBy,
                minimumDate,
                maximumDate
        );
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
                        case HttpURLConnection.HTTP_UNAUTHORIZED: {
                            mNotificationManager.notify(ERROR_NOTIFICATION_ID, getErrorNotification("Invalid API key").build());
                            break;
                        }
                        case HttpURLConnection.HTTP_NOT_FOUND: {
                            mNotificationManager.notify(ERROR_NOTIFICATION_ID, getErrorNotification("Resource not found").build());
                            break;
                        }
                        default:
                            mNotificationManager.notify(ERROR_NOTIFICATION_ID, getErrorNotification(String.valueOf(response.code())).build());
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
        Log.d(TAG, "onDownloadComplete: releaseDate = " + movies.get(0).getReleaseDate());

        mNotificationManager.cancel(DOWNLOAD_NOTIFICATION_ID);
        mNotificationManager.notify(DONE_NOTIFICATION_ID, getDoneNotification().build());
        sendIntent(movies, type);
    }

    private void sendIntent(List<Movie> movies, String type){
        Intent intent = new Intent(MoviesFragment.MESSAGE);
        intent.putExtra(RESULT_CODE, Activity.RESULT_OK);
        intent.putExtra(RESULT_TYPE, type);
        intent.putParcelableArrayListExtra(RESULT_MOVIES, (ArrayList<Movie>) movies);
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
