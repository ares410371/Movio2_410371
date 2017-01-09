package cz.muni.fi.pv256.movio2.uco_410371.movies;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.DownloadService;
import cz.muni.fi.pv256.movio2.uco_410371.adapters.HorizontalRecyclerViewAdapter;
import cz.muni.fi.pv256.movio2.uco_410371.models.Movie;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class MoviesPresenter extends BroadcastReceiver implements MoviesContract.Presenter {

    private MoviesContract.View mView;
    private FragmentActivity mFragmentActivity;
    private LocalBroadcastManager mBroadcastManager;
    private HorizontalRecyclerViewAdapter mAdapter;

    public MoviesPresenter(MoviesContract.View view, FragmentActivity fragmentActivity, LocalBroadcastManager broadcastManager) {
        mView = view;
        mFragmentActivity = fragmentActivity;
        mBroadcastManager = broadcastManager;
    }

    @Override
    public void startService() {
        Intent intent = new Intent(mFragmentActivity, DownloadService.class);
        mFragmentActivity.startService(intent);
    }

    @Override
    public void registerReceiver(HorizontalRecyclerViewAdapter adapter) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MoviesFragment.MESSAGE);
        mAdapter = adapter;
        if (mAdapter == null) {
            return;
        }
        mBroadcastManager.registerReceiver(this, intentFilter);
    }

    @Override
    public void unregisterReceiver() {
        if (mAdapter == null) {
            return;
        }
        mBroadcastManager.unregisterReceiver(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int resultCode = intent.getIntExtra(DownloadService.RESULT_CODE, RESULT_CANCELED);
        if (resultCode == RESULT_OK && intent.getAction().equals(MoviesFragment.MESSAGE)) {
            String type = intent.getStringExtra(DownloadService.RESULT_TYPE);
            ArrayList<Movie> movies = intent.getParcelableArrayListExtra(DownloadService.RESULT_MOVIES);
            List<Object> list = new ArrayList<>();
            list.add(type);
            list.add(movies);
            if (mAdapter == null) {
                return;
            }
            mAdapter.addItems(list);
        }
    }

}
