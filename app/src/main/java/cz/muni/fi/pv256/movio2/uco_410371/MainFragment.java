package cz.muni.fi.pv256.movio2.uco_410371;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.adapters.HorizontalRecyclerViewAdapter;
import cz.muni.fi.pv256.movio2.uco_410371.network.DownloadManager;
import cz.muni.fi.pv256.movio2.uco_410371.network.Singleton;

/**
 * Main Fragment
 * Created by Benjamin Varga on 6.10.2016.
 */

public class MainFragment extends Fragment {

    private static final String TAG = MainFragment.class.getName();

    private boolean mTwoPane;
    private DownloadManager downloadManager;

    public MainFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");

        downloadManager = new DownloadManager(getContext());
        downloadManager.startDownloadTask();
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


        List<Object> list = new ArrayList<>();

//        if (list.size() == 0) {
//            EmptyRecyclerViewAdapter emptyRecyclerViewAdapter = new EmptyRecyclerViewAdapter("NO DATA");
//            recyclerView.setAdapter(emptyRecyclerViewAdapter);
////        } else if (/*NO INTERNET*/) {
////            EmptyRecyclerViewAdapter emptyRecyclerViewAdapter = new EmptyRecyclerViewAdapter("NO INTERNET");
////            recyclerView.setAdapter(emptyRecyclerViewAdapter);
//        } else {
            HorizontalRecyclerViewAdapter horizontalRecyclerViewAdapter = new HorizontalRecyclerViewAdapter(getContext(), list, mTwoPane);
            Singleton.getInstance().setHorizontalRecyclerViewAdapter(horizontalRecyclerViewAdapter);
            recyclerView.setAdapter(horizontalRecyclerViewAdapter);
//        }

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
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
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

        downloadManager.cancelDownloadTask();
        downloadManager = null;
    }
}
