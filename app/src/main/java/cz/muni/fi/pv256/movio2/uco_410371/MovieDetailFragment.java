package cz.muni.fi.pv256.movio2.uco_410371;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cz.muni.fi.pv256.movio2.uco_410371.dummy.DummyContent;
import cz.muni.fi.pv256.movio2.uco_410371.model.Movie;

/**
 * MovieDetail Fragment
 * Created by Benjamin Varga on 6.10.2016.
 */

public class MovieDetailFragment extends Fragment {

    public static final String TAG = MovieDetailFragment.class.getName();
    public static final String ARG_MOVIE_ID = "movie_id";

    private Movie mMovie;

    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

        if (getArguments().containsKey(ARG_MOVIE_ID)) {
            mMovie = DummyContent.MOVIES.get(getArguments().getInt(ARG_MOVIE_ID, -1));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.movie_detail, container, false);

//        TextView movieTitleTV = (TextView)view.findViewById(R.id.movie_detail_title);
//        ImageView moviePosterIV = (ImageView)view.findViewById(R.id.movie_detail_poster);
//        ImageView moviePosterBackIV = (ImageView)view.findViewById(R.id.movie_detail_back_poster);
//
//        if (mMovie != null) {
//            movieTitleTV.setText(mMovie.getTitle());
//
//            switch (mMovie.getMovieId()) {
//                case 1 :
//                    moviePosterIV.setImageResource(R.drawable.dummyposter1);
//                    moviePosterBackIV.setImageResource(R.drawable.dummyback1);
//                    break;
//                case 2 :
//                    moviePosterIV.setImageResource(R.drawable.dummyposter2);
//                    moviePosterBackIV.setImageResource(R.drawable.dummyback2);
//                    break;
//                case 3 :
//                    moviePosterIV.setImageResource(R.drawable.dummyposter3);
//                    moviePosterBackIV.setImageResource(R.drawable.dummyback3);
//                    break;
//                case 4 :
//                    moviePosterIV.setImageResource(R.drawable.dummyposter4);
//                    moviePosterBackIV.setImageResource(R.drawable.dummyback4);
//                    break;
//                default:
//                    break;
//            }
//        }

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
}
