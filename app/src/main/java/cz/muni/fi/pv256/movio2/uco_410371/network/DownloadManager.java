package cz.muni.fi.pv256.movio2.uco_410371.network;

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
    private List<Movie> mMovies;

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
    }

    private static class DownloadTask extends AsyncTask<String, Void, List<Movie>> {

        private static final String MOVIE_DB_API_KEY = BuildConfig.MOVIE_DB_API_KEY;
        private static final String QUERY_API_KEY = "?api_key=";
        private static final String BASE_URL = "https://api.themoviedb.org/3/";
        private static final String UPCOMING_URL = "movie/upcoming";

        private final OkHttpClient mOkHttpClient = new OkHttpClient();

        @Override
        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute: ");
            super.onPreExecute();
        }

        @Override
        protected List<Movie> doInBackground(String... urls) {
            Log.d(TAG, "doInBackground: ");
            Request request = new Request.Builder()
                    .url(BASE_URL + UPCOMING_URL + QUERY_API_KEY + MOVIE_DB_API_KEY)
                    .get()
                    .addHeader("content-type", "application/json")
                    .build();

            try {
                Response response = mOkHttpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    return parseJson(response.body().string());
                }
            } catch (IOException ioe) {
                Log.e(TAG, "doInBackground: response exception", ioe);
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            Log.d(TAG, "onPostExecute: ");
            super.onPostExecute(movies);
            Singleton.getInstance().getMovieRecyclerViewAdapter().addItems(new ArrayList<Object>(movies));
            for (Movie movie: movies) {
                Log.d(TAG, movie.getTitle());
            }
        }

        @Override
        protected void onCancelled(List<Movie> movies) {
            Log.d(TAG, "onCancelled: ");
            super.onCancelled(movies);
        }
    }

    public void startDownloadTask() {
        if (!isTaskExecuting) {
            mDownloadTask = new DownloadTask();
            mDownloadTask.execute();
            isTaskExecuting = true;
        }
    }

    public void cancelDownloadTask() {
        if (isTaskExecuting) {
            mDownloadTask.cancel(true);
            isTaskExecuting = false;
        }
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

