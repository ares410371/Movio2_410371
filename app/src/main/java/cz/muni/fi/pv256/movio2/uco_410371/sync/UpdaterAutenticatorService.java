package cz.muni.fi.pv256.movio2.uco_410371.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class UpdaterAutenticatorService extends Service {

    private UpdaterAutenticator mUpdaterAutenticator;

    @Override
    public void onCreate() {
        mUpdaterAutenticator = new UpdaterAutenticator(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mUpdaterAutenticator.getIBinder();
    }
}
