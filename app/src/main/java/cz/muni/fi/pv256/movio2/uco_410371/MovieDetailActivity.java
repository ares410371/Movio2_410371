package cz.muni.fi.pv256.movio2.uco_410371;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

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
