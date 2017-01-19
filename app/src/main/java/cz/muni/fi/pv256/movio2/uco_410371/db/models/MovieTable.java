package cz.muni.fi.pv256.movio2.uco_410371.db.models;

public class MovieTable {

    private long mId;
    private String mTitle;
    private int mCategoryId;
    private int mTMDId;
    private String mPosterPath;
    private String mBackdropPath;
    private String mReleaseDate;
    private double mPopularity;
    private String mOverview;

    public MovieTable(long id, String title, int categoryId, int TMDId, String posterPath,
                      String backdropPath, String releaseDate, double popularity, String overview) {
        mId = id;
        mTitle = title;
        mCategoryId = categoryId;
        mTMDId = TMDId;
        mPosterPath = posterPath;
        mBackdropPath = backdropPath;
        mReleaseDate = releaseDate;
        mPopularity = popularity;
        mOverview = overview;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(int categoryId) {
        mCategoryId = categoryId;
    }

    public int getTMDId() {
        return mTMDId;
    }

    public void setTMDId(int TMDId) {
        mTMDId = TMDId;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(double popularity) {
        mPopularity = popularity;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieTable movie = (MovieTable) o;

        if (mId != movie.mId) return false;
        if (mCategoryId != movie.mCategoryId) return false;
        if (mTMDId != movie.mTMDId) return false;
        if (Double.compare(movie.mPopularity, mPopularity) != 0) return false;
        if (!mTitle.equals(movie.mTitle)) return false;
        if (mPosterPath != null ? !mPosterPath.equals(movie.mPosterPath) : movie.mPosterPath != null)
            return false;
        if (mBackdropPath != null ? !mBackdropPath.equals(movie.mBackdropPath) : movie.mBackdropPath != null)
            return false;
        if (mReleaseDate != null ? !mReleaseDate.equals(movie.mReleaseDate) : movie.mReleaseDate != null)
            return false;
        return mOverview != null ? mOverview.equals(movie.mOverview) : movie.mOverview == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + mTitle.hashCode();
        result = 31 * result + mCategoryId;
        result = 31 * result + mTMDId;
        result = 31 * result + (mPosterPath != null ? mPosterPath.hashCode() : 0);
        result = 31 * result + (mBackdropPath != null ? mBackdropPath.hashCode() : 0);
        result = 31 * result + (mReleaseDate != null ? mReleaseDate.hashCode() : 0);
        temp = Double.doubleToLongBits(mPopularity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (mOverview != null ? mOverview.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MovieTable{" +
                "mId=" + mId +
                ", mTitle='" + mTitle + '\'' +
                ", mCategoryId=" + mCategoryId +
                ", mTMDId=" + mTMDId +
                ", mPosterPath='" + mPosterPath + '\'' +
                ", mBackdropPath='" + mBackdropPath + '\'' +
                ", mReleaseDate='" + mReleaseDate + '\'' +
                ", mPopularity=" + mPopularity +
                ", mOverview='" + mOverview + '\'' +
                '}';
    }
}
