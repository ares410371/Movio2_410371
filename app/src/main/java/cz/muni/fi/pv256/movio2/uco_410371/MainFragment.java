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
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        if (view.findViewById(R.id.movie_detail_container) != null) {
            mTwoPane = true;
        }
        Log.d(TAG, "onCreateView: mTwoPane=" + (mTwoPane ? "true" : "false"));


        Button changeThemeBtn = (Button) view.findViewById(R.id.button_theme);
        changeThemeBtn.setOnClickListener(this);

        Button movie1Btn = (Button) view.findViewById(R.id.button_movie1);
        movie1Btn.setOnClickListener(this);

        Button movie2Btn = (Button) view.findViewById(R.id.button_movie2);
        movie2Btn.setOnClickListener(this);

        Button movie3Btn = (Button) view.findViewById(R.id.button_movie3);
        movie3Btn.setOnClickListener(this);

        Button movie4Btn = (Button) view.findViewById(R.id.button_movie4);
        movie4Btn.setOnClickListener(this);

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
                if (mTwoPane) {
                    Bundle args = new Bundle();
                    args.putInt(MovieDetailFragment.ARG_MOVIE_ID, 1);
                    MovieDetailFragment fragment = new MovieDetailFragment();
                    fragment.setArguments(args);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.movie_detail_container, fragment)
                            .commit();
                } else {
                    Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                    intent.putExtra(MovieDetailFragment.ARG_MOVIE_ID, 1);
                    getActivity().startActivity(intent);
                }
                break;
            case R.id.button_movie2 :
                break;
            case R.id.button_movie3 :
                break;
            case R.id.button_movie4 :
                break;
            default:
                break;
        }
    }
}
