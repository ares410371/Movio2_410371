package cz.muni.fi.pv256.movio2.uco_410371.network;

import cz.muni.fi.pv256.movio2.uco_410371.adapters.CombineRecyclerViewAdapter;

/**
 * Created by Benjamin Varga on 19.10.2016.
 */
public class Singleton {

    private static Singleton mInstance = null;

    private CombineRecyclerViewAdapter mCombineRecyclerViewAdapter;

    public static Singleton getInstance() {
        if (mInstance == null) {
            mInstance = new Singleton();
        }
        return mInstance;
    }

    public CombineRecyclerViewAdapter getCombineRecyclerViewAdapter() {
        return mCombineRecyclerViewAdapter;
    }

    public void setCombineRecyclerViewAdapter(CombineRecyclerViewAdapter combineRecyclerViewAdapter) {
        mCombineRecyclerViewAdapter = combineRecyclerViewAdapter;
    }
}
