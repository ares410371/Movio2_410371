package cz.muni.fi.pv256.movio2.uco_410371;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * MainActivity
 * Created by Benjamin Varga on 20.9.2016.
 */
public class MainActivity extends AppCompatActivity {

    private static final String MOVIE_DB_API_KEY = BuildConfig.MOVIE_DB_API_KEY;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences("ConfigTheme", Context.MODE_PRIVATE);
        if (mSharedPreferences.getBoolean("theme", false)){
            setTheme(R.style.AppTheme_Inverse);
        } else {
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_main);

        //Log.i("API_KEY", MOVIE_DB_API_KEY);
    }
}
