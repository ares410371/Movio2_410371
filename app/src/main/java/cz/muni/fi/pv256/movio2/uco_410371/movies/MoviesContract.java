package cz.muni.fi.pv256.movio2.uco_410371.movies;

import cz.muni.fi.pv256.movio2.uco_410371.movies.adapters.CategoryRVAdapter;

public interface MoviesContract {

    interface View {

    }

    interface Presenter {

        void startService();

        void registerReceiver(CategoryRVAdapter adapter);

        void unregisterReceiver();

    }
}
