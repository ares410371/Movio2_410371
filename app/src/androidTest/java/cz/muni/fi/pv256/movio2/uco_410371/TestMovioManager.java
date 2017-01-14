package cz.muni.fi.pv256.movio2.uco_410371;

import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import cz.muni.fi.pv256.movio2.uco_410371.db.MovioManager;
import cz.muni.fi.pv256.movio2.uco_410371.db.MovioProvider;
import cz.muni.fi.pv256.movio2.uco_410371.db.models.MovieTable;
import cz.muni.fi.pv256.movio2.uco_410371.models.Movie;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class TestMovioManager {

    private MovioManager mMovioManager;

    @Before
    public void setUp() throws Exception {
        mMovioManager = new MovioManager(getTargetContext());
    }

    @After
    public void tearDown() throws Exception {
        getTargetContext().getContentResolver().delete(
                MovioProvider.CONTENT_URI_MOVIE,
                null,
                null
        );
    }

    @Test
    public void testCreateMovie() throws Exception {
        Movie movie = new Movie(
                2468,
                "2016-11-19",
                "posterPath",
                "TestMovie",
                "backdropPath",
                7.42f,
                "lorem ipsum"
        );

        MovieTable movieTable = new MovieTable(
                0,
                movie.getTitle(),
                0,
                movie.getId(),
                movie.getPosterPath(),
                movie.getBackdropPath(),
                movie.getReleaseDate(),
                movie.getPopularity(),
                movie.getOverview()
        );

        mMovioManager.createMovie(movieTable);

        MovieTable movieByTitle = mMovioManager.getMovieByTitle(movieTable.getTitle());

        assertEquals("Movie doesn't created.", movieTable, movieByTitle);
    }

    @Test
    public void testDeleteMovie() throws Exception {
        Movie movie = new Movie(
                11257,
                "2016-11-28",
                "posterPath",
                "TestMovie #2",
                "backdropPath",
                4.42f,
                "info info"
        );

        MovieTable movieTable = new MovieTable(
                0,
                movie.getTitle(),
                0,
                movie.getId(),
                movie.getPosterPath(),
                movie.getBackdropPath(),
                movie.getReleaseDate(),
                movie.getPopularity(),
                movie.getOverview()
        );

        mMovioManager.createMovie(movieTable);

        mMovioManager.deleteMovie(movieTable);

        MovieTable movieByTitle = mMovioManager.getMovieByTitle(movieTable.getTitle());

        assertNull("Movie isn't deleted.", movieByTitle);
    }
}
