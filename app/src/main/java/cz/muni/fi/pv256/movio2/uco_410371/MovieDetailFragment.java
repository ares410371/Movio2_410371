package cz.muni.fi.pv256.movio2.uco_410371;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cz.muni.fi.pv256.movio2.uco_410371.dummy.DummyContent;
import cz.muni.fi.pv256.movio2.uco_410371.model.Movie;

/**
 * Created by Benjamin Varga on 6.10.2016.
 */

public class MovieDetailFragment extends Fragment {

    public static final String ARG_MOVIE_ID = "movie_id";

    private Movie mMovie;

    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_MOVIE_ID)) {
            mMovie = DummyContent.MOVIES.get(getArguments().getInt(ARG_MOVIE_ID, -1));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_detail, container, false);

        if (mMovie != null) {
            ((TextView) view.findViewById(R.id.item_detail)).setText(mMovie.getTitle());
        }

        return view;
    }
}
