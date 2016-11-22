package cz.muni.fi.pv256.movio2.uco_410371.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Arrays;

public class MovioProvider extends ContentProvider {

    private static final String TAG = MovioProvider.class.getName();
    private static final String CATEGORY_PATH = "category";
    private static final int CATEGORY = 100;
    private static final int CATEGORY_ID = 101;
    private static final String MOVIE_PATH = "movie";
    private static final int MOVIE = 200;
    private static final int MOVIE_ID = 201;
    private static final String CONTENT_AUTHORITY = "cz.muni.fi.pv256.movio2.uco_410371";
    private static final UriMatcher sUriMatcher = getUriMatcher();

    private static UriMatcher getUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(CONTENT_AUTHORITY, CATEGORY_PATH, CATEGORY);
        matcher.addURI(CONTENT_AUTHORITY, CATEGORY_PATH + "/#", CATEGORY_ID);
        matcher.addURI(CONTENT_AUTHORITY, MOVIE_PATH, MOVIE);
        matcher.addURI(CONTENT_AUTHORITY, MOVIE_PATH + "/#", MOVIE_ID);

        return matcher;
    }

    public static final Uri CONTENT_URI_CATEGORY = Uri.parse("content://" + CONTENT_AUTHORITY + "/" + CATEGORY_PATH);
    public static final Uri CONTENT_URI_MOVIE = Uri.parse("content://" + CONTENT_AUTHORITY + "/" + MOVIE_PATH);


    private MovioDbHelper mMovioDbHelper;

    @Override
    public boolean onCreate() {
        mMovioDbHelper = new MovioDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(TAG, Arrays.toString(selectionArgs));
        Cursor retCursor;

        switch (sUriMatcher.match(uri)) {
            case CATEGORY_ID: {
                retCursor = mMovioDbHelper.getReadableDatabase().query(
                        MovioDbHelper.TABLE_CATEGORY, projection,
                        "id" + " = '" + ContentUris.parseId(uri) + "'",
                        null, null, null, sortOrder
                );
                break;
            }
            case CATEGORY: {
                retCursor = mMovioDbHelper.getReadableDatabase().query(
                        MovioDbHelper.TABLE_CATEGORY, projection,
                        selection, selectionArgs,
                        null, null, sortOrder
                );
                break;
            }
            case MOVIE_ID: {
                retCursor = mMovioDbHelper.getReadableDatabase().query(
                        MovioDbHelper.TABLE_MOVIE, projection,
                        "id" + " = '" + ContentUris.parseId(uri) + "'",
                        null, null, null, sortOrder
                );
                break;
            }
            case MOVIE: {
                retCursor = mMovioDbHelper.getReadableDatabase().query(
                        MovioDbHelper.TABLE_MOVIE, projection,
                        selection, selectionArgs,
                        null, null, sortOrder
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case CATEGORY:
                return "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + CATEGORY_PATH;
            case CATEGORY_ID:
                return "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + CATEGORY_PATH;
            case MOVIE:
                return "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + MOVIE_PATH;
            case MOVIE_ID:
                return "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + MOVIE_PATH;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d(TAG, values.toString());
        final SQLiteDatabase db = mMovioDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case CATEGORY: {
                long id = db.insert(MovioDbHelper.TABLE_CATEGORY, null, values);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(CONTENT_URI_CATEGORY, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case MOVIE: {
                long id = db.insert(MovioDbHelper.TABLE_MOVIE, null, values);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(CONTENT_URI_MOVIE, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mMovioDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;

        switch (match) {
            case CATEGORY: {
                rowsDeleted = db.delete(MovioDbHelper.TABLE_CATEGORY, selection, selectionArgs);
                break;
            }
            case MOVIE: {
                rowsDeleted = db.delete(MovioDbHelper.TABLE_MOVIE, selection, selectionArgs);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (selection == null || rowsDeleted != 0)
            getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mMovioDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;

        switch (match) {
            case CATEGORY: {
                rowsUpdated = db.update(MovioDbHelper.TABLE_CATEGORY, values, selection, selectionArgs);
                break;
            }
            case MOVIE: {
                rowsUpdated = db.update(MovioDbHelper.TABLE_MOVIE, values, selection, selectionArgs);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsUpdated != 0)
            getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }
}
