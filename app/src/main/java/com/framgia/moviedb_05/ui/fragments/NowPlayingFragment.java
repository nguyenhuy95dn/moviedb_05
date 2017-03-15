package com.framgia.moviedb_05.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.framgia.moviedb_05.BuildConfig;
import com.framgia.moviedb_05.R;
import com.framgia.moviedb_05.data.model.Movie;
import com.framgia.moviedb_05.data.model.MoviesResponse;
import com.framgia.moviedb_05.service.MovieService;
import com.framgia.moviedb_05.service.ServiceGenerator;
import com.framgia.moviedb_05.ui.activity.DetailActivity;
import com.framgia.moviedb_05.ui.adapter.NowPlayingAdapter;
import com.framgia.moviedb_05.ui.interactor.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlayingFragment extends Fragment {
    private static final String TAG = NowPlayingFragment.class.getSimpleName();
    private int mPage = 1;
    private List<Movie> mMovies = new ArrayList<>();
    private NowPlayingAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public static NowPlayingFragment newInstance() {
        return new NowPlayingFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_now_playing, container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View v) {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_now_playing);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setupRecycleView();
        requestDatas();
    }

    private void setupRecycleView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent i = DetailActivity.getMovieIntent(getContext(), movie);
                startActivity(i);
            }
        });
    }

    private void requestDatas() {
        ServiceGenerator
            .createService(MovieService.class)
            .getNowPlayingMovies(BuildConfig.API_KEY, mPage)
            .enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call,
                                       Response<MoviesResponse> response) {
                    loadDataView(response.body());
                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), R.string.msg_error, Toast
                        .LENGTH_SHORT).show();
                }
            });
    }

    private void loadDataView(MoviesResponse listMovie) {
        if (listMovie == null) return;
        mMovies.addAll(listMovie.getResults());
        mAdapter.notifyDataSetChanged();
    }
}
