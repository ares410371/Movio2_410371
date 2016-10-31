package cz.muni.fi.pv256.movio2.uco_410371;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Set;

/**
 * MainActivity
 * Created by Benjamin Varga on 20.9.2016.
 */
public class MainActivity extends AppCompatActivity {

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, MainPreferences.class));
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            Set<String> set = preferences.getStringSet("pref_genres", null);
            if (set != null) {
                for (String s : set) {
                    Log.d(MainActivity.class.getName(), "onOptionsItemSelected: " + s);
                }
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
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
