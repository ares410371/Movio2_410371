package cz.muni.fi.pv256.movio2.uco_410371.movies;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cz.muni.fi.pv256.movio2.uco_410371.R;
import cz.muni.fi.pv256.movio2.uco_410371.movies.adapters.CategoryRVAdapter;

public class MoviesFragment extends Fragment implements MoviesContract.View {

    public static final String MESSAGE = "message";

    private static final String TAG = MoviesFragment.class.getName();

    private boolean mTwoPane;
    private RecyclerView mRecyclerView;
    private CategoryRVAdapter mRecyclerViewAdapter;
    private MoviesContract.Presenter mPresenter;
    private LinearLayout mContentFrame;
    private Parcelable mRecyclerViewState;

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setRetainInstance(true);
        mPresenter = new MoviesPresenter(this, getActivity(), LocalBroadcastManager.getInstance(getActivity()));
        mPresenter.startService("upcoming");
        mPresenter.startService("nowplaying");
        mPresenter.startService("popular");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.movies_fragment, container, false);

        if (view.findViewById(R.id.movie_detail_container) != null) {
            mTwoPane = true;
        }



        if (savedInstanceState != null) {
            mRecyclerView.getLayoutManager().onRestoreInstanceState(mRecyclerViewState);
        } else {
            mRecyclerView = (RecyclerView) view.findViewById(R.id.movies_recycler_view);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            mRecyclerViewAdapter = new CategoryRVAdapter(getContext(), mTwoPane);
            mRecyclerView.setAdapter(mRecyclerViewAdapter);
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
        mPresenter.registerReceiver(mRecyclerViewAdapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
        mPresenter.unregisterReceiver();
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mRecyclerViewState = mRecyclerView.getLayoutManager().onSaveInstanceState();
        super.onSaveInstanceState(outState);
    }

}
