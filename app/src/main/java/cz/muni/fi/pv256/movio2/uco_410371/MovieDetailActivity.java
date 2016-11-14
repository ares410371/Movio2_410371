package cz.muni.fi.pv256.movio2.uco_410371;

import android.content.ContentValues;
import android.net.Uri;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cz.muni.fi.pv256.movio2.uco_410371.db.MovioManager;
import cz.muni.fi.pv256.movio2.uco_410371.models.Movie;

public class MovieDetailActivity extends AppCompatActivity
        implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

    public static final String TAG = MovieDetailActivity.class.getName();

    private ImageView mMoviePosterIV;
    private TextView mMovieTitleTVExpanded;
    private TextView mMovieTitleTVCollapsed;
    private ImageView mMoviePosterBackIV;
    private boolean isHeaderVisible;
    private FloatingActionButton mFab;
    private boolean isInDB;
    private Movie mMovie;

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

        initView();

        //// TODO: 14.11.2016 nefunguje po zmene sa nezovola setmovie



        mMovie = getIntent().getParcelableExtra(MovieDetailFragment.ARG_MOVIE);
        if (mMovie != null) setMovieDetail(mMovie);

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
            MovioManager manager = new MovioManager(this);
            if (isInDB) {
                // TODO: 13.11.2016 remove
                // TODO: 14.11.2016 opravit mazanie nefunguje
                mFab.setImageResource(R.mipmap.ic_add_circle);

                if (mMovie != null) {
                    manager.deleteMovie(convertMovieToDB(mMovie));
                    isInDB = false;
                } else {
                    throw new NullPointerException("mMovie is null. Doesn't saved to database.");
                }
            } else {
                mFab.setImageResource(R.mipmap.ic_delete);

                if (mMovie != null /*&& ifNotExists(manager, mMovie)*/) { //// TODO: 14.11.2016 vyriesit duplikaty
                    manager.createMovie(convertMovieToDB(mMovie));
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
        mMovieTitleTVExpanded = (TextView) findViewById(R.id.text_movie_title_expanded);
        mMovieTitleTVCollapsed = (TextView) findViewById(R.id.text_movie_title_collapsed);
        mMoviePosterIV = (ImageView) findViewById(R.id.image_movie_poster);
        mMoviePosterBackIV = (ImageView) findViewById(R.id.image_movie_back_poster);
        mFab = (FloatingActionButton) findViewById(R.id.fab_detail);
        mFab.setOnClickListener(this);
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

    private cz.muni.fi.pv256.movio2.uco_410371.db.models.Movie convertMovieToDB(Movie movie) {
        Log.d(TAG, "convertMovieToDB: string= " + movie.getReleaseDate());

        return new cz.muni.fi.pv256.movio2.uco_410371.db.models.Movie(
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

//    private boolean ifNotExists(MovioManager manager, Movie movie) {
//        List<cz.muni.fi.pv256.movio2.uco_410371.db.models.Movie> allMovies = manager.getAllMovies();
//        for (cz.muni.fi.pv256.movio2.uco_410371.db.models.Movie m: allMovies) {
//            if (m.getTMDId() == movie.getId())
//                return false;
//        }
//        return true;
//    }

}
