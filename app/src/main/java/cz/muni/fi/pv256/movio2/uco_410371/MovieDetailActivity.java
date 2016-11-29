package cz.muni.fi.pv256.movio2.uco_410371;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cz.muni.fi.pv256.movio2.uco_410371.models.Movie;

/**
 * MovieDetail Activity
 * Created by Benjamin Varga on 6.10.2016.
 */

public class MovieDetailActivity extends AppCompatActivity
        implements AppBarLayout.OnOffsetChangedListener {

    public static final String TAG = MovieDetailActivity.class.getName();

    private ImageView mMoviePosterIV;
    private TextView mMovieTitleTVExpanded;
    private TextView mMovieTitleTVCollapsed;
    private ImageView mMoviePosterBackIV;
    private boolean isHeaderVisible;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Log.d(TAG, "onCreate: ");

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initView();

        Movie movie = getIntent().getParcelableExtra(MovieDetailFragment.ARG_MOVIE);
        if (movie != null) setMovieDetail(movie);

        if (savedInstanceState == null) {
            Bundle args = new Bundle();
            args.putParcelable(MovieDetailFragment.ARG_MOVIE,
                    getIntent().getParcelableExtra(MovieDetailFragment.ARG_MOVIE));
            args.putBoolean(MovieDetailFragment.ARG_SCREEN_TYPE,
                    getIntent().getBooleanExtra(MovieDetailFragment.ARG_SCREEN_TYPE, false));
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit();
        }
    }

    private void initView() {
        mMovieTitleTVExpanded = (TextView) findViewById(R.id.text_movie_title_expanded);
        mMovieTitleTVCollapsed = (TextView) findViewById(R.id.text_movie_title_collapsed);
        mMoviePosterIV = (ImageView) findViewById(R.id.image_movie_poster);
        mMoviePosterBackIV = (ImageView) findViewById(R.id.image_movie_back_poster);
    }

    private void setMovieDetail(Movie movie) {
        mMovieTitleTVExpanded.setText(movie.getTitle());
        mMovieTitleTVCollapsed.setText(movie.getTitle());
        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w300" + movie.getBackdropPath())
                .placeholder(R.drawable.placeholder_poster)
                .into(mMoviePosterBackIV);
        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath())
                .placeholder(R.drawable.placeholder_poster)
                .into(mMoviePosterIV);
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

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
        Log.d(TAG, "onOffsetChanged: percentage = " + percentage);

        if (percentage >= 0.8f) {
            if (isHeaderVisible) {
                mMoviePosterIV.setVisibility(View.GONE);
                mMovieTitleTVExpanded.setVisibility(View.GONE);
                mMovieTitleTVCollapsed.setVisibility(View.VISIBLE);
                mFab.setVisibility(View.GONE);
                isHeaderVisible = !isHeaderVisible;
            }
        } else if (percentage < 0.8f) {
            if (!isHeaderVisible) {
                mMoviePosterIV.setVisibility(View.VISIBLE);
                mMovieTitleTVExpanded.setVisibility(View.VISIBLE);
                mMovieTitleTVCollapsed.setVisibility(View.GONE);
                mFab.setVisibility(View.VISIBLE);
                isHeaderVisible = !isHeaderVisible;
            }
        }
    }

}
