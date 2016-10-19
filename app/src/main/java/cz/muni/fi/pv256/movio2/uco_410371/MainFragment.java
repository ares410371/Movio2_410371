package cz.muni.fi.pv256.movio2.uco_410371;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.adapters.EmptyRecyclerViewAdapter;
import cz.muni.fi.pv256.movio2.uco_410371.adapters.MovieRecyclerViewAdapter;
import cz.muni.fi.pv256.movio2.uco_410371.network.DownloadManager;
import cz.muni.fi.pv256.movio2.uco_410371.network.Singleton;

/**
 * Main Fragment
 * Created by Benjamin Varga on 6.10.2016.
 */

public class MainFragment extends Fragment implements OnClickListener {

    private static final String TAG = MainFragment.class.getName();

    private boolean mTwoPane;

    public MainFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

        DownloadManager downloadManager = new DownloadManager();
        downloadManager.startDownloadTask();
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

        //List<Object> list = DummyContent.ITEMS;
        List<Object> list = new ArrayList<>();
        list.add("Upcoming Movies2");

        if (list == null || list.size() == 0) {
            EmptyRecyclerViewAdapter emptyRecyclerViewAdapter = new EmptyRecyclerViewAdapter("NO DATA");
            recyclerView.setAdapter(emptyRecyclerViewAdapter);
//        } else if (/*NO INTERNET*/) {
//            EmptyRecyclerViewAdapter emptyRecyclerViewAdapter = new EmptyRecyclerViewAdapter("NO INTERNET");
//            recyclerView.setAdapter(emptyRecyclerViewAdapter);
        } else {
            MovieRecyclerViewAdapter movieRecyclerViewAdapter = new MovieRecyclerViewAdapter(getContext(), list, mTwoPane);
            Singleton.getInstance().setMovieRecyclerViewAdapter(movieRecyclerViewAdapter);
            recyclerView.setAdapter(movieRecyclerViewAdapter);
        }

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
        DownloadManager downloadManager = new DownloadManager();
        downloadManager.cancelDownloadTask();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }

    @Override
    public void onClick(View view) {

//        switch (view.getId()) {
//            case R.id.button_theme :
//                selectTheme();
//                break;
//            default:
//                Log.e(TAG, "onClick: Bad view id.");
//                break;
//        }
    }

//    private void selectTheme() {
//        SharedPreferences sharedPreferences = getActivity()
//                .getSharedPreferences(MainActivity.PREF_CONFIG_THEME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putBoolean(MainActivity.PREF_THEME, !sharedPreferences.getBoolean(MainActivity.PREF_THEME, false));
//        editor.apply();
//        getActivity().recreate();
//    }

}
