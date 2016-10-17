package cz.muni.fi.pv256.movio2.uco_410371;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.adapters.EmptyRecyclerViewAdapter;
import cz.muni.fi.pv256.movio2.uco_410371.adapters.MovieRecyclerViewAdapter;
import cz.muni.fi.pv256.movio2.uco_410371.dummy.DummyContent;

/**
 * Main Fragment
 * Created by Benjamin Varga on 6.10.2016.
 */

public class MainFragment extends Fragment implements View.OnClickListener {

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

        List<Object> list = DummyContent.ITEMS;

        if (list == null || list.size() == 0) {
            EmptyRecyclerViewAdapter emptyRecyclerViewAdapter = new EmptyRecyclerViewAdapter("NO DATA");
            recyclerView.setAdapter(emptyRecyclerViewAdapter);
//        } else if (/*NO INTERNET*/) {
//            EmptyRecyclerViewAdapter emptyRecyclerViewAdapter = new EmptyRecyclerViewAdapter("NO INTERNET");
//            recyclerView.setAdapter(emptyRecyclerViewAdapter);
        } else {
            MovieRecyclerViewAdapter movieRecyclerViewAdapter = new MovieRecyclerViewAdapter(getContext(), list, mTwoPane);
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
//            case R.id.button_movie1 :
//                selectDetail(0);
//                break;
//            case R.id.button_movie2 :
//                selectDetail(1);
//                break;
//            case R.id.button_movie3 :
//                selectDetail(2);
//                break;
//            case R.id.button_movie4 :
//                selectDetail(3);
//                break;
//            default:
//                Log.e(TAG, "onClick: Bad view id.");
//                break;
//        }
    }

    private void selectTheme() {
        SharedPreferences sharedPreferences = getActivity()
                .getSharedPreferences(MainActivity.PREF_CONFIG_THEME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MainActivity.PREF_THEME, !sharedPreferences.getBoolean(MainActivity.PREF_THEME, false));
        editor.apply();
        getActivity().recreate();
    }

    private void selectDetail(int id) {
        if (mTwoPane) {
            Bundle args = new Bundle();
            args.putInt(MovieDetailFragment.ARG_MOVIE_ID, id);
            args.putBoolean(MovieDetailFragment.ARG_SCREEN_TYPE, true);
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(args);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
            intent.putExtra(MovieDetailFragment.ARG_MOVIE_ID, id);
            intent.putExtra(MovieDetailFragment.ARG_SCREEN_TYPE, false);
            getActivity().startActivity(intent);
        }
    }







}
