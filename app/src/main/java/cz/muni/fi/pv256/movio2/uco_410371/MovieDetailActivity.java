package cz.muni.fi.pv256.movio2.uco_410371;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Benjamin Varga on 6.10.2016.
 */

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);



        if (savedInstanceState == null) {
            Bundle args = new Bundle();
            args.putInt(MovieDetailFragment.ARG_MOVIE_ID,
                    getIntent().getIntExtra(MovieDetailFragment.ARG_MOVIE_ID, -1));
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit();
        }
    }


}
