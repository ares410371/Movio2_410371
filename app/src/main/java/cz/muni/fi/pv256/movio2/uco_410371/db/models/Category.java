package cz.muni.fi.pv256.movio2.uco_410371.db.models;

public class Category {

    private int mId;
    private String mName;

    public Category(int id, String name) {
        mId = id;
        mName = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (mId != category.mId) return false;
        return mName.equals(category.mName);

    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + mName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                '}';
    }
}
