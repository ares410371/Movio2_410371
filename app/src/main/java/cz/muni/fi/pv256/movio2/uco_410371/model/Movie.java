package cz.muni.fi.pv256.movio2.uco_410371.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Movie model
 * Created by Benjamin Varga on 6.10.2016.
 */

public class Movie implements Parcelable {

    private int mMovieId;
    private long mReleaseDate;
    private String mCoverPath;
    private String mTitle;
    private String mBackdrop;
    private float mPopularity;

    public Movie(int movieId, long releaseDate, String coverPath, String title, String backdrop, float popularity) {
        mMovieId = movieId;
        mReleaseDate = releaseDate;
        mCoverPath = coverPath;
        mTitle = title;
        mBackdrop = backdrop;
        mPopularity = popularity;
    }

    public int getMovieId() {
        return mMovieId;
    }

    public void setMovieId(int movieId) {
        mMovieId = movieId;
    }

    public long getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(long releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getCoverPath() {
        return mCoverPath;
    }

    public void setCoverPath(String coverPath) {
        mCoverPath = coverPath;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getBackdrop() {
        return mBackdrop;
    }

    public void setBackdrop(String backdrop) {
        mBackdrop = backdrop;
    }

    public float getPopularity() {
        return mPopularity;
    }

    public void setPopularity(float popularity) {
        mPopularity = popularity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Movie(Parcel in) {
        mMovieId = in.readInt();
        mReleaseDate = in.readLong();
        mCoverPath = in.readString();
        mTitle = in.readString();
        mBackdrop = in.readString();
        mPopularity = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mMovieId);
        dest.writeLong(mReleaseDate);
        dest.writeString(mCoverPath);
        dest.writeString(mTitle);
        dest.writeString(mBackdrop);
        dest.writeFloat(mPopularity);
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
