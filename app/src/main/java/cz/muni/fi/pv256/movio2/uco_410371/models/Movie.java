package cz.muni.fi.pv256.movio2.uco_410371.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Movie model
 * Created by Benjamin Varga on 6.10.2016.
 */
@JsonObject
public class Movie implements Parcelable {

    @JsonField(name = "id")
    private int mId;

    @JsonField(name = "release_date")
    private String mReleaseDate;

    @JsonField(name = "poster_path")
    private String mPosterPath;

    @JsonField(name = "title")
    private String mTitle;

    @JsonField(name = "backdrop_path")
    private String mBackdropPath;

    @JsonField(name = "vote_average")
    private float mPopularity;

    @JsonField(name = "overview")
    private String mOverview;

    public Movie() {}

    public Movie(int id, String releaseDate, String posterPath, String title, String backdropPath, float popularity, String overview) {
        mId = id;
        mReleaseDate = releaseDate;
        mPosterPath = posterPath;
        mTitle = title;
        mBackdropPath = backdropPath;
        mPopularity = popularity;
        mOverview = overview;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public float getPopularity() {
        return mPopularity;
    }

    public void setPopularity(float popularity) {
        mPopularity = popularity;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Movie(Parcel in) {
        mId = in.readInt();
        mReleaseDate = in.readString();
        mPosterPath = in.readString();
        mTitle = in.readString();
        mBackdropPath = in.readString();
        mPopularity = in.readFloat();
        mOverview = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mReleaseDate);
        dest.writeString(mPosterPath);
        dest.writeString(mTitle);
        dest.writeString(mBackdropPath);
        dest.writeFloat(mPopularity);
        dest.writeString(mOverview);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

}
