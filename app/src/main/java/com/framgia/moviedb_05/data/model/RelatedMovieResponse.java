package com.framgia.moviedb_05.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RelatedMovieResponse {
    @SerializedName("id")
    private int mId;
    @SerializedName("page")
    private int mPage;
    @SerializedName("results")
    private List<RelatedMovie> mResults;
    @SerializedName("total_pages")
    private int mTotalPages;
    @SerializedName("total_results")
    private int mTotalResults;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public List<RelatedMovie> getResults() {
        return mResults;
    }

    public void setResults(List<RelatedMovie> results) {
        mResults = results;
    }

    public int getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(int totalPages) {
        mTotalPages = totalPages;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(int totalResults) {
        mTotalResults = totalResults;
    }
}
