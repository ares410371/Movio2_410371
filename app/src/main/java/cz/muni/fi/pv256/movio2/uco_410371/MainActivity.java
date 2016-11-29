package cz.muni.fi.pv256.movio2.uco_410371;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * MainActivity
 * Created by Benjamin Varga on 20.9.2016.
 */
public class MainActivity extends AppCompatActivity {

//    private static final String MOVIE_DB_API_KEY = BuildConfig.MOVIE_DB_API_KEY;
    private static final String TAG  = MainActivity.class.getName();
    public static final String PREF_CONFIG_THEME = "PREF_CONFIG_THEME";
    public static final String PREF_THEME = "PREF_THEME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_CONFIG_THEME, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(PREF_THEME, false)){
            setTheme(R.style.AppTheme_Inverse);
        } else {
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_main);

//        Log.d(TAG, "onCreate: API_KEY = " + MOVIE_DB_API_KEY);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

}
