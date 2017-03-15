package com.framgia.moviedb_05.data.model;

import com.google.gson.annotations.SerializedName;

public class RelatedMovie {
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("title")
    private String mTitle;

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
