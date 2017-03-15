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

import com.framgia.moviedb_05.R;
import com.framgia.moviedb_05.data.model.Movie;
import com.framgia.moviedb_05.data.model.MovieSQLite;
import com.framgia.moviedb_05.ui.activity.DetailActivity;
import com.framgia.moviedb_05.ui.adapter.NowPlayingAdapter;
import com.framgia.moviedb_05.ui.interactor.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    private static final String TAG = FavoritesFragment.class.getSimpleName();
    private List<Movie> mList = new ArrayList<>();
    private NowPlayingAdapter mAdapter;
    private MovieSQLite mSQLite;
    private RecyclerView mRecyclerView;

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    private void initViews() {
        mSQLite = new MovieSQLite(getContext());
        mList.clear();
        mList = mSQLite.getAllFavorite();
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_favorites);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new NowPlayingAdapter(mList, R.layout.item_list, getContext());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent i = DetailActivity.getMovieIntent(getContext(), movie);
                startActivity(i);
            }
        });
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        initViews();
    }
}
