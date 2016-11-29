package cz.muni.fi.pv256.movio2.uco_410371;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cz.muni.fi.pv256.movio2.uco_410371.dummy.DummyContent;
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
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initView();

        Movie movie = DummyContent.MOVIES.get(getIntent().getIntExtra(MovieDetailFragment.ARG_MOVIE_ID, -1));
        if (movie != null) setMovieDetail(movie);

        if (savedInstanceState == null) {
            Bundle args = new Bundle();
            args.putInt(MovieDetailFragment.ARG_MOVIE_ID,
                    getIntent().getIntExtra(MovieDetailFragment.ARG_MOVIE_ID, -1));
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
        switch (movie.getMovieId()) {
            case 1:
                Picasso.with(this).load(R.drawable.dummyposter1).into(mMoviePosterIV);
                Picasso.with(this).load(R.drawable.dummyback1).into(mMoviePosterBackIV);
                break;
            case 2:
                Picasso.with(this).load(R.drawable.dummyposter2).into(mMoviePosterIV);
                Picasso.with(this).load(R.drawable.dummyback2).into(mMoviePosterBackIV);
                break;
            case 3:
                Picasso.with(this).load(R.drawable.dummyposter3).into(mMoviePosterIV);
                Picasso.with(this).load(R.drawable.dummyback3).into(mMoviePosterBackIV);
                break;
            case 4:
                Picasso.with(this).load(R.drawable.dummyposter4).into(mMoviePosterIV);
                Picasso.with(this).load(R.drawable.dummyback4).into(mMoviePosterBackIV);
                break;
            default:
                Log.e(TAG, "setMovieDetail: Wrong movie ID.");
                break;
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
