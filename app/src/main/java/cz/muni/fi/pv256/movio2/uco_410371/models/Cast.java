package cz.muni.fi.pv256.movio2.uco_410371.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class Cast {

    @JsonField(name = "cast_id")
    private int mCastId;
    @JsonField(name = "character")
    private String mCharacter;
    @JsonField(name = "credit_id")
    private String mCreditId;
    @JsonField(name = "id")
    private int mId;
    @JsonField(name = "name")
    private String mName;
    @JsonField(name = "order")
    private int mOrder;
    @JsonField(name = "profile_path")
    private String mProfilePath;

    public Cast() {
    }

    public int getCastId() {
        return mCastId;
    }

    public void setCastId(int castId) {
        mCastId = castId;
    }

    public String getCharacter() {
        return mCharacter;
    }

    public void setCharacter(String character) {
        mCharacter = character;
    }

    public String getCreditId() {
        return mCreditId;
    }

    public void setCreditId(String creditId) {
        mCreditId = creditId;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getOrder() {
        return mOrder;
    }

    public void setOrder(int order) {
        mOrder = order;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }
}
