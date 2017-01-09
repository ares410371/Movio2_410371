package cz.muni.fi.pv256.movio2.uco_410371;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cz.muni.fi.pv256.movio2.uco_410371.db.MovioManager;
import cz.muni.fi.pv256.movio2.uco_410371.db.models.MovieTable;
import cz.muni.fi.pv256.movio2.uco_410371.models.Movie;

public class MovieDetailActivity extends AppCompatActivity
        implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

    public static final String TAG = MovieDetailActivity.class.getName();

    private ImageView mMoviePosterIV;
    private ImageView mMoviePosterBackIV;
    private TextView mMovieTitleTVExpanded;
    private TextView mMovieTitleTVCollapsed;
    private TextView mMovieDateTV;
    private boolean isHeaderVisible;
    private FloatingActionButton mFab;
    private boolean isInDB;
    private MovieTable mMovieTable;
    private MovioManager mManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity);
        Log.d(TAG, "onCreate: ");

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(this);

        initView();

        Movie movie = getIntent().getParcelableExtra(MovieDetailFragment.ARG_MOVIE);
        if (movie != null) {
            setMovieDetail(movie);
            mMovieTable = convertMovieToDB(movie);
        }

        MovieTable movieByTitle = mManager.getMovieByTitle(mMovieTable.getTitle());
        if (movieByTitle != null) {
            mMovieTable = movieByTitle;
            isInDB = true;
            mFab.setImageResource(R.mipmap.ic_delete);
        }


        if (savedInstanceState == null) {
            Bundle args = new Bundle();
            args.putParcelable(MovieDetailFragment.ARG_MOVIE, movie);
            args.putBoolean(MovieDetailFragment.ARG_SCREEN_TYPE,
                    getIntent().getBooleanExtra(MovieDetailFragment.ARG_SCREEN_TYPE, false));
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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab_detail) {

            if (isInDB) {
                // // TODO: 18.11.2016 potrebujem null check??? 
                mFab.setImageResource(R.mipmap.ic_add_circle);

                if (mMovieTable != null) {
                    mManager.deleteMovie(mMovieTable);
                    isInDB = false;
                } else {
                    throw new NullPointerException("mMovie is null. Doesn't saved to database.");
                }
            } else {
                mFab.setImageResource(R.mipmap.ic_delete);

                if (mMovieTable != null) {
                    mManager.createMovie(mMovieTable);
                    isInDB = true;
                } else {
                    throw new NullPointerException("mMovie is null. Doesn't saved to database.");
                }
            }
        }
    }


    //*************************
    //*****Private Methods*****

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        mMovieTitleTVExpanded = (TextView) findViewById(R.id.text_movie_title_expanded);
        mMovieTitleTVCollapsed = (TextView) findViewById(R.id.text_movie_title_collapsed);
        mMoviePosterIV = (ImageView) findViewById(R.id.image_movie_poster);
        mMoviePosterBackIV = (ImageView) findViewById(R.id.image_movie_back_poster);
        mMovieDateTV = (TextView) findViewById(R.id.text_movie_year);
        mFab = (FloatingActionButton) findViewById(R.id.fab_detail);
        mFab.setOnClickListener(this);
        mManager = new MovioManager(this);
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
        mMovieDateTV.setText(movie.getReleaseDate());
    }

    private MovieTable convertMovieToDB(Movie movie) {
        Log.d(TAG, "convertMovieToDB: string= " + movie.getReleaseDate());

        return new MovieTable(
                0,
                movie.getTitle(),
                0,
                movie.getId(),
                movie.getPosterPath(),
                movie.getBackdropPath(),
                movie.getReleaseDate(),
                movie.getPopularity()
        );
    }

}
