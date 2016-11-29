package cz.muni.fi.pv256.movio2.uco_410371;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cz.muni.fi.pv256.movio2.uco_410371.models.Movie;

public class MovieDetailFragment extends Fragment
        implements AppBarLayout.OnOffsetChangedListener{

    public static final String TAG = MovieDetailFragment.class.getName();
    public static final String ARG_MOVIE = "movie";
    public static final String ARG_SCREEN_TYPE = "screen_type";

    private Movie mMovie;
    private boolean mTwoPane;
    private TextView mMovieTitleTVExpanded;
    private ImageView mMoviePosterIV;
    private ImageView mMoviePosterBackIV;
    private boolean isHeaderVisible;
    private FloatingActionButton mFab;

    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

        if (getArguments().containsKey(ARG_MOVIE)) {
            mMovie = getArguments().getParcelable(ARG_MOVIE);
        }

        if (getArguments().containsKey(ARG_SCREEN_TYPE)) {
            mTwoPane = getArguments().getBoolean(ARG_SCREEN_TYPE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view;

        if (mTwoPane) {
            view = inflater.inflate(R.layout.movie_detail_twopane, container, false);

            AppBarLayout appBarLayout = (AppBarLayout) view.findViewById(R.id.app_bar);
            appBarLayout.addOnOffsetChangedListener(this);

            mFab = (FloatingActionButton) view.findViewById(R.id.fab);
            mFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action Tablet", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            mMovieTitleTVExpanded = (TextView)view.findViewById(R.id.text_movie_title_expanded);
            mMoviePosterIV = (ImageView)view.findViewById(R.id.image_movie_poster);
            mMoviePosterBackIV = (ImageView)view.findViewById(R.id.image_movie_back_poster);

            if (mMovie != null) {
                mMovieTitleTVExpanded.setText(mMovie.getTitle());

                Picasso.with(getContext())
                        .load("https://image.tmdb.org/t/p/w300" + mMovie.getBackdropPath())
                        .placeholder(R.drawable.placeholder_poster)
                        .into(mMoviePosterBackIV);
                Picasso.with(getContext())
                        .load("https://image.tmdb.org/t/p/w500" + mMovie.getPosterPath())
                        .placeholder(R.drawable.placeholder_poster)
                        .into(mMoviePosterIV);
            }

        } else {
            view = inflater.inflate(R.layout.movie_detail, container, false);
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
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
                mFab.setVisibility(View.GONE);
                isHeaderVisible = !isHeaderVisible;
            }
        } else if (!isHeaderVisible) {
            mMoviePosterIV.setVisibility(View.VISIBLE);
            mMovieTitleTVExpanded.setVisibility(View.VISIBLE);
            mFab.setVisibility(View.VISIBLE);
            isHeaderVisible = !isHeaderVisible;
        }
    }
}
