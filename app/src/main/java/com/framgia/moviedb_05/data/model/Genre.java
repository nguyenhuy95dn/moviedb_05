package com.framgia.moviedb_05.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Age on 3/15/2017.
 */
public class Genre {
    @SerializedName("id")
    private Integer mId;
    @SerializedName("name")
    private String mName;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
