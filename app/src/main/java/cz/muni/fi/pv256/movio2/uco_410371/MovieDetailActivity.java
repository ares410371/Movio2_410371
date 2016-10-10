package cz.muni.fi.pv256.movio2.uco_410371;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import cz.muni.fi.pv256.movio2.uco_410371.dummy.DummyContent;
import cz.muni.fi.pv256.movio2.uco_410371.model.Movie;

/**
 * MovieDetail Activity
 * Created by Benjamin Varga on 6.10.2016.
 */

public class MovieDetailActivity extends AppCompatActivity {

    public static final String TAG = MovieDetailActivity.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Log.d(TAG, "onCreate: ");

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView movieTitleTV = (TextView)findViewById(R.id.movie_detail_title);
        ImageView moviePosterIV = (ImageView)findViewById(R.id.movie_detail_poster);
        ImageView moviePosterBackIV = (ImageView)findViewById(R.id.movie_detail_back_poster);

        Movie movie = DummyContent.MOVIES.get(getIntent().getIntExtra(MovieDetailFragment.ARG_MOVIE_ID, -1));
        if (movie != null) {
            movieTitleTV.setText(movie.getTitle());

            switch (movie.getMovieId()) {
                case 1 :
                    moviePosterIV.setImageResource(R.drawable.dummyposter1);
                    moviePosterBackIV.setImageResource(R.drawable.dummyback1);
                    break;
                case 2 :
                    moviePosterIV.setImageResource(R.drawable.dummyposter2);
                    moviePosterBackIV.setImageResource(R.drawable.dummyback2);
                    break;
                case 3 :
                    moviePosterIV.setImageResource(R.drawable.dummyposter3);
                    moviePosterBackIV.setImageResource(R.drawable.dummyback3);
                    break;
                case 4 :
                    moviePosterIV.setImageResource(R.drawable.dummyposter4);
                    moviePosterBackIV.setImageResource(R.drawable.dummyback4);
                    break;
                default:
                    break;
            }
        }

        if (savedInstanceState == null) {
            Bundle args = new Bundle();
            args.putInt(MovieDetailFragment.ARG_MOVIE_ID,
                    getIntent().getIntExtra(MovieDetailFragment.ARG_MOVIE_ID, -1));
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

}
