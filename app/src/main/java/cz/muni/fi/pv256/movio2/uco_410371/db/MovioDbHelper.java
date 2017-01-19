package cz.muni.fi.pv256.movio2.uco_410371.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovioDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "movioDB.db";
    private static final int DATABASE_VERSION = 2;

    //KEY_+TABLE_+COLUMN_NAME
    //*****CATEGORY TABLE******
    public static final String TABLE_CATEGORY = "category";
    public static final String KEY_CATEGORY_ID = "id";
    public static final String KEY_CATEGORY_NAME = "name";

    //*****MOVIE TABLE******
    public static final String TABLE_MOVIE = "movie";
    public static final String KEY_MOVIE_ID = "id";
    public static final String KEY_MOVIE_CATEGORY_ID = "category_id";
    public static final String KEY_MOVIE_TMD_ID = "tmd_id";
    public static final String KEY_MOVIE_TITLE = "title";
    public static final String KEY_MOVIE_POSTER_PATH = "poster_path";
    public static final String KEY_MOVIE_BACKDROP_PATH = "backdrop_path";
    public static final String KEY_MOVIE_RELEASE_DATE = "release_date";
    public static final String KEY_MOVIE_POPULARITY = "popularity";
    public static final String KEY_MOVIE_OVERVIEW = "overview";


    public MovioDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY + " ("
                + KEY_CATEGORY_ID + " INTEGER PRIMARY KEY, "
                + KEY_CATEGORY_NAME + " TEXT NOT NULL"
                + ")";

        db.execSQL(SQL_CREATE_CATEGORY);

        final String SQL_CREATE_MOVIE = "CREATE TABLE " + TABLE_MOVIE + " ("
                + KEY_MOVIE_ID + " INTEGER PRIMARY KEY, "
                + KEY_MOVIE_TITLE + " TEXT NOT NULL, "
                + KEY_MOVIE_CATEGORY_ID + " INTEGER, "
                + KEY_MOVIE_TMD_ID + " INTEGER, "
                + KEY_MOVIE_POSTER_PATH + " TEXT, "
                + KEY_MOVIE_BACKDROP_PATH + " TEXT, "
                + KEY_MOVIE_RELEASE_DATE + " TEXT, "
                + KEY_MOVIE_POPULARITY + " REAL, "
                + KEY_MOVIE_OVERVIEW + " TEXT, "
                + "FOREIGN KEY(" + KEY_MOVIE_CATEGORY_ID + ") REFERENCES " + TABLE_CATEGORY + "(" + KEY_CATEGORY_ID + ")"
                + ")";

        db.execSQL(SQL_CREATE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(db);
    }


}