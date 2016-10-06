package cz.muni.fi.pv256.movio2.uco_410371.dummy;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.model.Movie;

/**
 * Dummy data
 * Created by Benjamin Varga on 6.10.2016.
 */

public class DummyContent {

    public static final List<Movie> MOVIES = new ArrayList<>();

    static {
        MOVIES.add(new Movie(1, 1, "Path", "The GodFather", "Path", 5f));
        MOVIES.add(new Movie(2, 2, "Path", "Forest Gunn", "Path", 6f));
        MOVIES.add(new Movie(3, 3, "Path", "Iron Man", "Path", 4f));
        MOVIES.add(new Movie(4, 4, "Path", "The Magnificent Seven", "Path", 7f));
    }
}
