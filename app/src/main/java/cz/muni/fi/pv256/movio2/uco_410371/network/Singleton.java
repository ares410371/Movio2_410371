package cz.muni.fi.pv256.movio2.uco_410371.network;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.adapters.MovieRecyclerViewAdapter;
import cz.muni.fi.pv256.movio2.uco_410371.models.Movie;

/**
 * Created by Benjamin Varga on 19.10.2016.
 */
public class Singleton {

    private static Singleton mInstance = null;

    private MovieRecyclerViewAdapter mMovieRecyclerViewAdapter;
    private List<Object> mList;

    public static Singleton getInstance() {
        if (mInstance == null) {
            mInstance = new Singleton();
        }
        return mInstance;
    }

    public MovieRecyclerViewAdapter getMovieRecyclerViewAdapter() {
        return mMovieRecyclerViewAdapter;
    }

    public void setMovieRecyclerViewAdapter(MovieRecyclerViewAdapter movieRecyclerViewAdapter) {
        mMovieRecyclerViewAdapter = movieRecyclerViewAdapter;
    }

    public List<Object> getList() {
        return mList;
    }

    public void setList(List<Object> list) {
        mList = list;
    }
}
