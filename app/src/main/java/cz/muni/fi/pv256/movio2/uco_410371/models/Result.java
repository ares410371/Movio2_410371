package cz.muni.fi.pv256.movio2.uco_410371.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Created by Benjamin Varga on 18.10.2016.
 */

@JsonObject
public class Result {

    @JsonField(name = "results")
    private List<Movie> mMovies;

    public Result() {
    }

    public Result(List<Movie> movies) {
        mMovies = movies;
    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
    }
}
