package com.framgia.moviedb_05.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.framgia.moviedb_05.R;
import com.framgia.moviedb_05.data.model.Movie;

import java.io.Serializable;

public class DetailActivity extends AppCompatActivity {
    public static Intent getMovieIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(String.valueOf(R.string.movie_detail + ""), (Serializable) movie);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
}
