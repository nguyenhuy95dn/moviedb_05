package com.framgia.moviedb_05.data.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Age on 3/17/2017.
 */
public class MovieSQLite extends SQLiteOpenHelper {
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_TITLE = "Title";
    public static final String COLUMN_OVERVIEW = "Overview";
    public static final String COLUMN_POSTER_PATH = "PosterPath";
    public static final String COLUMN_VOTE_AVERAGER = "PosterPath";
    public static final String COLUMN_FAVORITES = "Favorites";
    private static final String COMMAND_DROP_DB = "Drop table if exist";
    private static final String DATABASE_NAME = "store10.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MOVIE = "tb_movie1";
    private final String TABLE_MOVIE_QUERY = "CREATE TABLE " + TABLE_MOVIE + "(" + COLUMN_ID + " " +
        "TEXT " +
        "PRIMARY KEY NOT NULL, " + COLUMN_TITLE + " TEXT, " + COLUMN_OVERVIEW +
        " TEXT, " + COLUMN_POSTER_PATH + " TEXT, " + COLUMN_VOTE_AVERAGER + " TEXT, " +
        COLUMN_FAVORITES + " TEXT)";

    public MovieSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_MOVIE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(COMMAND_DROP_DB + TABLE_MOVIE);
        onCreate(db);
    }

    public List<Movie> getAllFavorite() {
        List<Movie> mList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_MOVIE, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mList.add(new Movie(cursor));
            } while (cursor.moveToNext());
        }
        return mList;
    }

    public void insertFavorites(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, movie.getId());
        contentValues.put(COLUMN_TITLE, movie.getTitle());
        contentValues.put(COLUMN_OVERVIEW, movie.getOverview());
        contentValues.put(COLUMN_POSTER_PATH, movie.getPosterPath());
        contentValues.put(COLUMN_FAVORITES, movie.getFavorites());
        db.insert(TABLE_MOVIE, null, contentValues);
        db.close();
    }

    public void deleteFavorites(int movieId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOVIE, COLUMN_ID + "=?", new String[]{String.valueOf(movieId)});
    }
}
