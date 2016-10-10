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

    private static final String MOVIE_DB_API_KEY = BuildConfig.MOVIE_DB_API_KEY;
    public static final String TAG  = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        SharedPreferences sharedPreferences = getSharedPreferences("ConfigTheme", Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("theme", false)){
            setTheme(R.style.AppTheme_Inverse);
        } else {
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_main);

        //Log.i("API_KEY", MOVIE_DB_API_KEY);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
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

}
