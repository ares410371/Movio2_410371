package cz.muni.fi.pv256.movio2.uco_410371.network;

import cz.muni.fi.pv256.movio2.uco_410371.adapters.HorizontalRecyclerViewAdapter;

/**
 * Created by Benjamin Varga on 19.10.2016.
 */
public class Singleton {

    private static Singleton mInstance = null;

    private HorizontalRecyclerViewAdapter mHorizontalRecyclerViewAdapter;

    public static Singleton getInstance() {
        if (mInstance == null) {
            mInstance = new Singleton();
        }
        return mInstance;
    }

    public HorizontalRecyclerViewAdapter getHorizontalRecyclerViewAdapter() {
        return mHorizontalRecyclerViewAdapter;
    }

    public void setHorizontalRecyclerViewAdapter(HorizontalRecyclerViewAdapter horizontalRecyclerViewAdapter) {
        mHorizontalRecyclerViewAdapter = horizontalRecyclerViewAdapter;
    }
}
