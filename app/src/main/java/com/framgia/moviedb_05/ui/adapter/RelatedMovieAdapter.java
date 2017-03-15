package com.framgia.moviedb_05.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.moviedb_05.R;
import com.framgia.moviedb_05.data.model.RelatedMovie;
import com.framgia.moviedb_05.service.ServiceGenerator;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Age on 3/15/2017.
 */
public class RelatedMovieAdapter extends RecyclerView.Adapter<RelatedMovieAdapter.ViewHolder> {
    private List<RelatedMovie> mRelatedMovies;
    private Context mContext;
    private int mRowLayout;

    public RelatedMovieAdapter(List<RelatedMovie> relatedMovies, int rowLayout, Context context) {
        this.mRelatedMovies = relatedMovies;
        this.mRowLayout = rowLayout;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
            .from(parent.getContext()).inflate(R.layout.item_related_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return new RelatedMovieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RelatedMovie relatedMovie = mRelatedMovies.get(position);
        holder.bindData(relatedMovie);
    }

    @Override
    public int getItemCount() {
        return mRelatedMovies == null ? 0 : mRelatedMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.text_title_related_movie);
            mImageView = (ImageView) itemView.findViewById(R.id.image_related);
        }

        public void bindData(RelatedMovie relatedMovie) {
            if (relatedMovie == null) return;
            mTitle.setText(relatedMovie.getTitle());
            Picasso.with(mContext)
                .load(ServiceGenerator.BASE_IMAGE_URL + relatedMovie.getBackdropPath())
                .placeholder(R.drawable.bg_no_img)
                .into(mImageView);
        }
    }
}
