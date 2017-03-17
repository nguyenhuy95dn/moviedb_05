package com.framgia.moviedb_05.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.moviedb_05.R;
import com.framgia.moviedb_05.data.model.Movie;
import com.framgia.moviedb_05.service.ServiceGenerator;
import com.framgia.moviedb_05.ui.interactor.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.ViewHolder> {
    private List<Movie> mMovies;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private int mRowLayout;
    private OnItemClickListener mItemClickListener;

    public NowPlayingAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.mMovies = movies;
        this.mRowLayout = rowLayout;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        holder.bindData(movie);
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitle;
        private TextView mOverview;
        private ImageView mImageView;
        private TextView mTextvote;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.text_title);
            mOverview = (TextView) itemView.findViewById(R.id.text_overview);
            mImageView = (ImageView) itemView.findViewById(R.id.image_item);
            mTextvote = (TextView) itemView.findViewById(R.id.text_vote_average);
            itemView.setOnClickListener(this);
        }

        public void bindData(Movie movie) {
            if (movie == null) return;
            mTitle.setText(movie.getTitle());
            mOverview.setText(movie.getOverview());
            mTextvote.setText(movie.getVoteAverage());
            Picasso.with(mContext)
                .load(ServiceGenerator.BASE_IMAGE_URL + movie.getBackdropPath())
                .placeholder(R.drawable.bg_no_img)
                .into(mImageView);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(mMovies.get(getPosition()));
            }
        }
    }
}
