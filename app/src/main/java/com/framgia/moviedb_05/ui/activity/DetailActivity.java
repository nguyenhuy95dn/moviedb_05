package com.framgia.moviedb_05.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.moviedb_05.BuildConfig;
import com.framgia.moviedb_05.R;
import com.framgia.moviedb_05.data.model.Genre;
import com.framgia.moviedb_05.data.model.Movie;
import com.framgia.moviedb_05.data.model.RelatedMovie;
import com.framgia.moviedb_05.data.model.RelatedMovieResponse;
import com.framgia.moviedb_05.service.MovieService;
import com.framgia.moviedb_05.service.ServiceGenerator;
import com.framgia.moviedb_05.ui.adapter.CustomPagerAdapter;
import com.framgia.moviedb_05.ui.adapter.RelatedMovieAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.getSimpleName();
    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    private CustomPagerAdapter mCustomPagerAdapter;
    private List<String> mImageUrl = new ArrayList<>();
    private ViewPager mViewPager;
    private List<Genre> mGenres = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RelatedMovieAdapter mRelatedMovieAdapter;
    private List<RelatedMovie> mRelatedMovies = new ArrayList<>();
    private TextView mOverview;
    private Movie mMovie;

    public static Intent getMovieIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        loadDetail();
    }

    private void loadDetail() {
        ServiceGenerator
            .createService(MovieService.class)
            .getMovieDetails(mMovie.getId(), BuildConfig.API_KEY)
            .enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    if (response == null || response.body() == null) return;
                    mOverview.setText(response.body().getOverview());
                    loadListRelatedMovie(response.body());
                    loadViewPager(response.body());
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    Toast.makeText(DetailActivity.this, R.string.msg_error, Toast
                        .LENGTH_SHORT).show();
                }
            });
    }

    private void loadViewPager(Movie movie) {
        if (movie == null) return;
        mImageUrl.add(movie.getPosterPath());
        mImageUrl.add(movie.getBackdropPath());
        mCustomPagerAdapter.notifyDataSetChanged();
    }

    private void loadListRelatedMovie(Movie movie) {
        if (movie == null) return;
        mGenres = movie.getGenres();
        ServiceGenerator.createService(MovieService.class)
            .getRelatedMovie(mGenres.get(0).getId(), BuildConfig.API_KEY)
            .enqueue(new Callback<RelatedMovieResponse>() {
                @Override
                public void onResponse(Call<RelatedMovieResponse> call,
                                       Response<RelatedMovieResponse> response) {
                    if (response == null || response.body() == null) return;
                    loadRelatedDataView(response.body());
                }

                @Override
                public void onFailure(Call<RelatedMovieResponse> call, Throwable t) {
                    Toast.makeText(DetailActivity.this, R.string.msg_error, Toast
                        .LENGTH_SHORT).show();
                }
            });
    }

    private void initView() {
        mOverview = (TextView) findViewById(R.id.text_overview_description);
        mMovie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        setTitle(mMovie.getTitle());
        setupRecycleView();
        setupViewPager();
    }

    private void setupViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mCustomPagerAdapter = new CustomPagerAdapter(this, mImageUrl);
        mViewPager.setAdapter(mCustomPagerAdapter);
    }

    private void setupRecycleView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_related_movie);
        LinearLayoutManager layoutManager =
            new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRelatedMovieAdapter = new RelatedMovieAdapter(getApplicationContext()
            , R.layout.item_related_list, mRelatedMovies);
        mRecyclerView.setAdapter(mRelatedMovieAdapter);
    }

    private void loadRelatedDataView(RelatedMovieResponse listRelated) {
        if (listRelated == null) return;
        mRelatedMovies.addAll(listRelated.getResults());
        mRelatedMovieAdapter.notifyDataSetChanged();
    }
}
