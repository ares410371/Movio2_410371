package cz.muni.fi.pv256.movio2.uco_410371.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.bluelinelabs.logansquare.LoganSquare;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.BuildConfig;
import cz.muni.fi.pv256.movio2.uco_410371.models.Movie;
import cz.muni.fi.pv256.movio2.uco_410371.models.Result;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * DownloadManager class
 * Created by Benjamin Varga on 18.10.2016.
 */
public class DownloadManager {

    private static final String TAG = DownloadManager.class.getName();
    private boolean isTaskExecuting;
    private DownloadTask mDownloadTask;
    private Context mContext;
    private List<Movie> mMovies;

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
    }

    public DownloadManager(Context context) {
        mContext = context;
    }

    private static class DownloadTask extends AsyncTask<String, Void, List<Object>> {

        private static final String MOVIE_DB_API_KEY = BuildConfig.MOVIE_DB_API_KEY;
        private static final String QUERY_API_KEY = "?api_key=" + MOVIE_DB_API_KEY;
        private static final String BASE_URL = "https://api.themoviedb.org/3/";
        private static final String UPCOMING_URL = "movie/upcoming";
        private static final String POPULAR_URL = "movie/popular";

        private final OkHttpClient mOkHttpClient = new OkHttpClient();

        @Override
        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute: ");
            super.onPreExecute();
        }

        @Override
        protected List<Object> doInBackground(String... urls) {
            Log.d(TAG, "doInBackground: ");
            List<Object> objects = new ArrayList<>();

            for (String url : urls) {
                Request request = new Request.Builder()
                        .url(BASE_URL + getUrl(url) + QUERY_API_KEY)
                        .get()
                        .addHeader("content-type", "application/json")
                        .build();

                try {
                    Response response = mOkHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        List<Movie> movies = parseJson(response.body().string());
                        objects.add(url);
                        objects.add(movies);
                    }
                } catch (IOException ioe) {
                    Log.e(TAG, "doInBackground: response exception", ioe);
                }
            }

            return objects;
        }

        private String getUrl(String url) {
            if (url.startsWith("Upcoming")) {
                return UPCOMING_URL;
            } else if (url.startsWith("Popular")) {
                return POPULAR_URL;
            } else {
                return "";
            }
        }

        @Override
        protected void onPostExecute(List<Object> objects) {
            Log.d(TAG, "onPostExecute: ");
            super.onPostExecute(objects);
            Singleton.getInstance().getHorizontalRecyclerViewAdapter().addItems(objects);
        }

        @Override
        protected void onCancelled(List<Object> objects) {
            Log.d(TAG, "onCancelled: ");
            super.onCancelled(objects);
        }
    }

    public void startDownloadTask() {
        if (!isTaskExecuting) {
            mDownloadTask = new DownloadTask();
            mDownloadTask.execute("Upcoming Movies", "Popular Movies");
            isTaskExecuting = true;
        }
    }

    public void cancelDownloadTask() {
        if (isTaskExecuting) {
            mDownloadTask.cancel(true);
            isTaskExecuting = false;
        }
    }

    private boolean isInternetConnection() {
        ConnectivityManager manager =
                (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return (networkInfo != null) && (networkInfo.isConnectedOrConnecting());
    }

    private static List<Movie> parseJson(String json) {
        List<Movie> movies = null;

        Result result = null;
        try {
            result = LoganSquare.parse(json, Result.class);
        } catch (IOException ioe) {
            Log.e(TAG, "parseJson: parse exception", ioe);
        }

        if (result != null) {
            movies = result.getMovies();
        }

        return movies;
    }

}

