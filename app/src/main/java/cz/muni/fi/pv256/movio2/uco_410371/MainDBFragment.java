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
import android.widget.Toast;

import cz.muni.fi.pv256.movio2.uco_410371.adapters.HorizontalRecyclerViewAdapter;

public class MainDBFragment extends Fragment {

    //*****CONSTANT*****
    public static final String TAG = MainDBFragment.class.getName();

    private boolean mTwoPane;

    public MainDBFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
        Toast.makeText(context, "DB fragment", Toast.LENGTH_SHORT).show();
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

        HorizontalRecyclerViewAdapter horizontalRecyclerViewAdapter = new HorizontalRecyclerViewAdapter(getContext(), mTwoPane, "db");
        recyclerView.setAdapter(horizontalRecyclerViewAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(MainDBFragment.TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(MainDBFragment.TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(MainDBFragment.TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(MainDBFragment.TAG, "onStop: ");
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

}
