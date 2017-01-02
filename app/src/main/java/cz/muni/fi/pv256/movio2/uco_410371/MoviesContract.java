package cz.muni.fi.pv256.movio2.uco_410371;

import cz.muni.fi.pv256.movio2.uco_410371.adapters.HorizontalRecyclerViewAdapter;

public interface MoviesContract {

    interface View {

    }

    interface Presenter {
        void startService();
        void registerReceiver(HorizontalRecyclerViewAdapter adapter);
        void unregisterReceiver();

    }
}
