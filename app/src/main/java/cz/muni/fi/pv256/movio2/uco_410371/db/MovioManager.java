package cz.muni.fi.pv256.movio2.uco_410371.db;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.db.models.MovieTable;

public class MovioManager {

    private static final String WHERE_ID = MovioDbHelper.KEY_MOVIE_ID + " = ?";
    private static final String[] MOVIE_COLUMS = {
            MovioDbHelper.KEY_MOVIE_ID,
            MovioDbHelper.KEY_MOVIE_TITLE,
            MovioDbHelper.KEY_MOVIE_CATEGORY_ID,
            MovioDbHelper.KEY_MOVIE_TMD_ID,
            MovioDbHelper.KEY_MOVIE_POSTER_PATH,
            MovioDbHelper.KEY_MOVIE_BACKDROP_PATH,
            MovioDbHelper.KEY_MOVIE_RELEASE_DATE,
            MovioDbHelper.KEY_MOVIE_POPULARITY,
            MovioDbHelper.KEY_MOVIE_OVERVIEW
    };

    private Context mContext;

    public MovioManager(Context context) {
        mContext = context.getApplicationContext();
    }

    public void createMovie(MovieTable movie) {
        if (movie == null) throw new NullPointerException("movie is null.");

        movie.setId((int) ContentUris.parseId(mContext.getContentResolver().insert(MovioProvider.CONTENT_URI_MOVIE, prepareValues(movie))));
    }

    public void updateMovie(MovieTable movie) {
        if (movie == null) throw new NullPointerException("movie is null.");

        mContext.getContentResolver().update(MovioProvider.CONTENT_URI_MOVIE, prepareValues(movie), WHERE_ID, new String[]{String.valueOf(movie.getId())});
    }

    public void deleteMovie(MovieTable movie) {
        if (movie == null) throw new NullPointerException("movie is null.");

        mContext.getContentResolver().delete(MovioProvider.CONTENT_URI_MOVIE, WHERE_ID, new String[]{String.valueOf(movie.getId())});
    }

    public List<MovieTable> getAllMovies() {
        Cursor cursor = mContext.getContentResolver().query(MovioProvider.CONTENT_URI_MOVIE, MOVIE_COLUMS, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            List<MovieTable> movies = new ArrayList<>(cursor.getCount());
            try {
                while (!cursor.isAfterLast()) {
                    movies.add(getMovie(cursor));
                    cursor.moveToNext();
                }
            } finally {
                cursor.close();
            }
            return movies;
        }

        return Collections.emptyList();
    };

    public MovieTable getMovieByTitle(String title) {
        for (MovieTable movie : getAllMovies()) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }

//    public List<MovieTable> getMoviesByCategory(int categoryId) {
//
//    }

    //*************************
    //*****Private Methods*****

    private ContentValues prepareValues(MovieTable movieTable) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovioDbHelper.KEY_MOVIE_TMD_ID, movieTable.getTMDId());
        contentValues.put(MovioDbHelper.KEY_MOVIE_POSTER_PATH, movieTable.getPosterPath());
        contentValues.put(MovioDbHelper.KEY_MOVIE_BACKDROP_PATH, movieTable.getBackdropPath());
        contentValues.put(MovioDbHelper.KEY_MOVIE_TITLE, movieTable.getTitle());
        contentValues.put(MovioDbHelper.KEY_MOVIE_RELEASE_DATE, movieTable.getReleaseDate());
        contentValues.put(MovioDbHelper.KEY_MOVIE_POPULARITY, movieTable.getPopularity());
        contentValues.put(MovioDbHelper.KEY_MOVIE_OVERVIEW, movieTable.getOverview());
        //// TODO: 14.11.2016 doplnit category id
        return contentValues;
    }

    private MovieTable getMovie(Cursor cursor) {
        return new MovieTable(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getInt(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getDouble(7),
                cursor.getString(8)
        );
    }
}
