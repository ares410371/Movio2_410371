package cz.muni.fi.pv256.movio2.uco_410371.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import cz.muni.fi.pv256.movio2.uco_410371.BuildConfig;
import cz.muni.fi.pv256.movio2.uco_410371.R;
import cz.muni.fi.pv256.movio2.uco_410371.sync.UpdaterSyncAdapter;
import cz.muni.fi.pv256.movio2.uco_410371.util.ActivityUtils;

public class MoviesActivity extends AppCompatActivity {

    private static final String TAG  = MoviesActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.logging)
            Log.d(TAG, "onCreate: ");

        if (BuildConfig.theme) {
            setTheme(R.style.AppTheme);
        } else {
            setTheme(R.style.AppTheme_Inverse);
        }

        setContentView(R.layout.movies_activity);

        UpdaterSyncAdapter.initializeSyncAdapter(this);

        MoviesFragment moviesFragment = (MoviesFragment) getSupportFragmentManager().findFragmentById(R.id.movies_fragment_container);
        if (moviesFragment == null) {
            // Create new fragment
            moviesFragment = MoviesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), moviesFragment, R.id.movies_fragment_container);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        final MenuItem toggleservice = menu.findItem(R.id.action_toggle);
        final Switch actionView = (Switch) toggleservice.getActionView();

        actionView.setChecked(true);
        actionView.setText(R.string.action_toogle_on);

        actionView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    buttonView.setText(R.string.action_toogle_on);
                    ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(),
                            MoviesFragment.newInstance(), R.id.movies_fragment_container);
                } else {
                    buttonView.setText(R.string.action_toggle_off);
                    ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(),
                            MoviesDBFragment.newInstance(), R.id.movies_fragment_container);
                }
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            UpdaterSyncAdapter.syncImmediately(this);
            return true;
        } else if (id == R.id.action_settings) {
            // TODO setting bonus
//            startActivity(new Intent(MoviesActivity.this, MoviesPreferences.class));
//            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//            Set<String> set = preferences.getStringSet("pref_genres", null);
//            if (set != null) {
//                for (String s : set) {
//                    Log.d(MoviesActivity.class.getName(), "onOptionsItemSelected: " + s);
//                }
//            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (BuildConfig.logging)
            Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (BuildConfig.logging)
            Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (BuildConfig.logging)
            Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (BuildConfig.logging)
            Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (BuildConfig.logging)
            Log.d(TAG, "onDestroy: ");
    }
}
