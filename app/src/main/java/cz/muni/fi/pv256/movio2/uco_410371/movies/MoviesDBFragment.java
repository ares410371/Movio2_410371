package cz.muni.fi.pv256.movio2.uco_410371.movies;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.R;
import cz.muni.fi.pv256.movio2.uco_410371.adapters.MovieVERTRecyclerViewAdapter;
import cz.muni.fi.pv256.movio2.uco_410371.db.MovioManager;
import cz.muni.fi.pv256.movio2.uco_410371.db.models.MovieTable;
import cz.muni.fi.pv256.movio2.uco_410371.models.Movie;

public class MoviesDBFragment extends Fragment {

    //*****CONSTANT*****
    public static final String TAG = MoviesDBFragment.class.getName();

    private boolean mTwoPane;

    public MoviesDBFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
        Toast.makeText(context, "DB fragment", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.movies_fragment, container, false);

        if (view.findViewById(R.id.movie_detail_container) != null) {
            mTwoPane = true;
        }

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.movies_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<MovieTable> movieTables = new MovioManager(getContext()).getAllMovies();

        MovieVERTRecyclerViewAdapter movieVERTRecyclerViewAdapter = new MovieVERTRecyclerViewAdapter(getContext(), convertMovieTableToMovie(movieTables), mTwoPane);
        recyclerView.setAdapter(movieVERTRecyclerViewAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(MoviesDBFragment.TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(MoviesDBFragment.TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(MoviesDBFragment.TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(MoviesDBFragment.TAG, "onStop: ");
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

    private List<Movie> convertMovieTableToMovie(List<MovieTable> movieTables) {
        List<Movie> converted = new ArrayList<>();
        for (MovieTable mt : movieTables) {
            converted.add(new Movie(mt.getTMDId(), mt.getReleaseDate(), mt.getPosterPath(),
                    mt.getTitle(), mt.getBackdropPath(), (float)mt.getPopularity()));
        }
        return converted;
    }
}
