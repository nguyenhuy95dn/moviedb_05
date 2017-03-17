package com.framgia.moviedb_05.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.framgia.moviedb_05.BuildConfig;
import com.framgia.moviedb_05.R;
import com.framgia.moviedb_05.data.model.Movie;
import com.framgia.moviedb_05.data.model.MoviesResponse;
import com.framgia.moviedb_05.service.MovieService;
import com.framgia.moviedb_05.service.ServiceGenerator;
import com.framgia.moviedb_05.ui.adapter.NowPlayingAdapter;
import com.framgia.moviedb_05.ui.interactor.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = SearchActivity.class.getSimpleName();
    private static final String QUERY = "query";
    private int mPage = 1;
    private List<Movie> mMovies = new ArrayList<>();
    private LinearLayoutManager mLinearLayout;
    private NowPlayingAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private String mQuery;
    private ProgressBar mProgressBar;

    public static Intent searchIntent(Context context, String query) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(QUERY, query);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        mQuery = getIntent().getStringExtra(QUERY);
        Toast.makeText(this, mQuery, Toast.LENGTH_SHORT).show();
        mProgressBar = (ProgressBar) findViewById(R.id.progessbar);
        setTitle(mQuery);
        setupRecycleView();
        showProgress(true);
        requestDatas();
        loadMoreData();
    }

    public void showProgress(boolean show) {
        if (show) mProgressBar.setVisibility(View.VISIBLE);
        else mProgressBar.setVisibility(View.GONE);
    }

    private void loadMoreData() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (mLinearLayout.findLastCompletelyVisibleItemPosition() == mMovies.size() - 1) {
                    mPage++;
                    requestDatas();
                }
            }
        });
    }

    private void setupRecycleView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.re_view_search);
        mLinearLayout = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayout);
        mAdapter = new NowPlayingAdapter(mMovies, R.layout.item_list, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent i = DetailActivity.getMovieIntent(getApplicationContext(), movie);
                startActivity(i);
            }
        });
    }

    private void requestDatas() {
        showProgress(true);
        ServiceGenerator
            .createService(MovieService.class)
            .searchMovie(BuildConfig.API_KEY, mQuery, mPage)
            .enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call,
                                       Response<MoviesResponse> response) {
                    if (response == null || response.body() == null) return;
                    loadDataView(response.body());
                    showProgress(false);
                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    Log.e(TAG, t.toString());
                }
            });
    }

    private void loadDataView(MoviesResponse movie) {
        mMovies.addAll(movie.getResults());
        mAdapter.notifyDataSetChanged();
    }
}
