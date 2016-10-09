package cz.muni.fi.pv256.movio2.uco_410371;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Main Fragment
 * Created by Benjamin Varga on 6.10.2016.
 */

public class MainFragment extends Fragment implements View.OnClickListener{

    public static final String TAG = MainFragment.class.getName();

    private boolean mTwoPane;

    public MainFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        if (view.findViewById(R.id.movie_detail_container) != null) {
            mTwoPane = true;
        }
        Log.d(TAG, "onCreateView: mTwoPane=" + (mTwoPane ? "true" : "false"));


        view.findViewById(R.id.button_theme).setOnClickListener(this);
        view.findViewById(R.id.button_movie1).setOnClickListener(this);
        view.findViewById(R.id.button_movie2).setOnClickListener(this);
        view.findViewById(R.id.button_movie3).setOnClickListener(this);
        view.findViewById(R.id.button_movie4).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_theme :
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ConfigTheme", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("theme", !sharedPreferences.getBoolean("theme", false));
                editor.apply();
                getActivity().recreate();
                break;
            case R.id.button_movie1 :
                selectDetail(0);
                break;
            case R.id.button_movie2 :
                selectDetail(1);
                break;
            case R.id.button_movie3 :
                selectDetail(2);
                break;
            case R.id.button_movie4 :
                selectDetail(3);
                break;
            default:
                break;
        }
    }

    private void selectDetail(int id) {
        if (mTwoPane) {
            Bundle args = new Bundle();
            args.putInt(MovieDetailFragment.ARG_MOVIE_ID, id);
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(args);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
            intent.putExtra(MovieDetailFragment.ARG_MOVIE_ID, id);
            getActivity().startActivity(intent);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
    }

}
