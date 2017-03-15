package com.framgia.moviedb_05.data.model;

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.moviedb_05.data.model.MovieSQLite.COLUMN_FAVORITES;
import static com.framgia.moviedb_05.data.model.MovieSQLite.COLUMN_ID;
import static com.framgia.moviedb_05.data.model.MovieSQLite.COLUMN_OVERVIEW;
import static com.framgia.moviedb_05.data.model.MovieSQLite.COLUMN_POSTER_PATH;
import static com.framgia.moviedb_05.data.model.MovieSQLite.COLUMN_TITLE;
import static com.framgia.moviedb_05.data.model.MovieSQLite.COLUMN_VOTE_AVERAGER;

public class Movie implements Serializable {
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("genre_ids")
    private List<Integer> mGenreIds = new ArrayList<Integer>();
    @SerializedName("genres")
    private List<Genre> mGenres;
    @SerializedName("id")
    private Integer mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("vote_average")
    private String mVoteAverage;
    private int mFavorites;

    public Movie() {
    }

    public Movie(String posterPath, String overview, String releaseDate,
                 List<Integer> genreIds, List<Genre> genres, Integer id, String title,
                 String backdropPath, String voteAverage, int favorites) {
        mPosterPath = posterPath;
        mOverview = overview;
        mReleaseDate = releaseDate;
        mGenreIds = genreIds;
        mGenres = genres;
        mId = id;
        mTitle = title;
        mBackdropPath = backdropPath;
        mVoteAverage = voteAverage;
        mFavorites = favorites;
    }

    public Movie(Cursor cursor) {
        mId = Integer.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
        mTitle = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
        mOverview = cursor.getString(cursor.getColumnIndex(COLUMN_OVERVIEW));
        mPosterPath = cursor.getString(cursor.getColumnIndex(COLUMN_POSTER_PATH));
        mVoteAverage = cursor.getString(cursor.getColumnIndex(COLUMN_VOTE_AVERAGER));
        mFavorites = Integer.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_FAVORITES)));
    }

    public String getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        mVoteAverage = voteAverage;
    }

    public int getFavorites() {
        return mFavorites;
    }

    public void setFavorites(int favorites) {
        mFavorites = favorites;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        this.mPosterPath = posterPath;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        this.mOverview = overview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.mReleaseDate = releaseDate;
    }

    public List<Integer> getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.mGenreIds = genreIds;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genre> genres) {
        mGenres = genres;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.mBackdropPath = backdropPath;
    }
}