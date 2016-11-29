package cz.muni.fi.pv256.movio2.uco_410371.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin Varga on 29.10.2016.
 */
@JsonObject
public class MovieCredits {

    @JsonField(name = "id")
    private int mId;

    @JsonField(name = "cast")
    private List<Cast> mCasts;

    @JsonField(name = "crew")
    private List<Crew> mCrews;

    public MovieCredits() {
        mCasts = new ArrayList<>();
        mCrews = new ArrayList<>();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public List<Cast> getCasts() {
        return mCasts;
    }

    public void setCasts(List<Cast> casts) {
        mCasts = casts;
    }

    public List<Crew> getCrews() {
        return mCrews;
    }

    public void setCrews(List<Crew> crews) {
        mCrews = crews;
    }
}

