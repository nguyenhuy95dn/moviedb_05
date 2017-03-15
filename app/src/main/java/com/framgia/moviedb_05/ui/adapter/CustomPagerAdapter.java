package com.framgia.moviedb_05.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.framgia.moviedb_05.R;
import com.framgia.moviedb_05.service.ServiceGenerator;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Age on 3/10/2017.
 */
public class CustomPagerAdapter extends PagerAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<String> mImageUrl;

    public CustomPagerAdapter(Context context, List<String> imageUrl) {
        mContext = context;
        mImageUrl = imageUrl;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mImageUrl == null ? 0 : mImageUrl.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = mLayoutInflater.inflate(R.layout.pager_item, container, false);
        assert v != null;
        ImageView imageView = (ImageView) v.findViewById(R.id.image_poster_backdrop);
        Picasso.with(mContext)
            .load(ServiceGenerator.BASE_IMAGE_URL + mImageUrl.get(position))
            .placeholder(R.drawable.bg_no_img)
            .into(imageView);
        container.addView(v, 0);
        return v;
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }
}
