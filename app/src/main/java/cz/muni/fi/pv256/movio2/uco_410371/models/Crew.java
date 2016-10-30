package cz.muni.fi.pv256.movio2.uco_410371.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class Crew {

    @JsonField(name = "credit_id")
    private String mCreditId;

    @JsonField(name = "department")
    private String mDepartment;

    @JsonField(name = "id")
    private int mId;

    @JsonField(name = "job")
    private String mJob;

    @JsonField(name = "name")
    private String mName;

    @JsonField(name = "profile_path")
    private String mProfilePath;

    public Crew() {
    }

    public String getCreditId() {
        return mCreditId;
    }

    public void setCreditId(String creditId) {
        mCreditId = creditId;
    }

    public String getDepartment() {
        return mDepartment;
    }

    public void setDepartment(String department) {
        mDepartment = department;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getJob() {
        return mJob;
    }

    public void setJob(String job) {
        mJob = job;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }
}
