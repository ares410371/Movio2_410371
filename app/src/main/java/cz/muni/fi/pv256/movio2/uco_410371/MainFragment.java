package cz.muni.fi.pv256.movio2.uco_410371;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.adapters.HorizontalRecyclerViewAdapter;
import cz.muni.fi.pv256.movio2.uco_410371.models.Movie;
import cz.muni.fi.pv256.movio2.uco_410371.network.DownloadService;

public class MainFragment extends Fragment {

    //*****CONSTANT*****
    public static final String MESSAGE = "message";
    public static final String TAG = MainFragment.class.getName();
    public static final String TYPE_NET = "net";

    private boolean mTwoPane;
    private LocalBroadcastManager mBroadcastManager;
    private HorizontalRecyclerViewAdapter mRecyclerViewAdapter;

    public MainFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
        mBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        startService();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        if (view.findViewById(R.id.movie_detail_container) != null) {
            mTwoPane = true;
        }

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerViewAdapter = new HorizontalRecyclerViewAdapter(getContext(), mTwoPane, TYPE_NET);
        recyclerView.setAdapter(mRecyclerViewAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        registerReceiver();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
        unregisterReceiver();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }

    //*****PRIVATE METHODS*****

    private void startService() {
        Intent intent = new Intent(getActivity(), DownloadService.class);
        getActivity().startService(intent);
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MESSAGE);
        mBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);
    }

    private void unregisterReceiver() {
        mBroadcastManager.unregisterReceiver(mBroadcastReceiver);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int resultCode = intent.getIntExtra(DownloadService.RESULT_CODE, Activity.RESULT_CANCELED);
            if (resultCode == Activity.RESULT_OK && intent.getAction().equals(MESSAGE)) {
                String type = intent.getStringExtra(DownloadService.RESULT_TYPE);
                ArrayList<Movie> movies = intent.getParcelableArrayListExtra(DownloadService.RESULT_MOVIES);
                List<Object> list = new ArrayList<>();
                list.add(type);
                list.add(movies);
                mRecyclerViewAdapter.addItems(list);
            }
        }
    };
}
