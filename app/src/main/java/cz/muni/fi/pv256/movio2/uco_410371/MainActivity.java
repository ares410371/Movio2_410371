package cz.muni.fi.pv256.movio2.uco_410371;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Benjamin Varga on 20.9.2016.
 */
public class MainActivity extends AppCompatActivity {

    private static final String MOVIE_DB_API_KEY = BuildConfig.MOVIE_DB_API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Log.i("API_KEY", MOVIE_DB_API_KEY);
    }
}
