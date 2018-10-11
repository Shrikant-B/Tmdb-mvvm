package com.shrikantbadwaik.tmdb.view.moviedetails;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shrikantbadwaik.tmdb.BR;
import com.shrikantbadwaik.tmdb.R;
import com.shrikantbadwaik.tmdb.data.model.Image;
import com.shrikantbadwaik.tmdb.databinding.LayoutMovieDetailsViewHolderBinding;
import com.shrikantbadwaik.tmdb.viewmodel.MovieDetailsAdapterViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieDetailsViewPagerAdapter extends PagerAdapter {
    private List<Image> imageList;

    @Inject
    public MovieDetailsViewPagerAdapter() {
        imageList = new ArrayList<>();
    }

    public void setImageList(List<Image> imageList) {
        this.imageList.addAll(imageList);
    }

    public void clear() {
        imageList.clear();
    }

    @Override
    public int getCount() {
        if (imageList == null) return 0;

        if (imageList.size() >= 5) {
            return 5;
        } else return imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutMovieDetailsViewHolderBinding adapterBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()),
                R.layout.layout_movie_details_view_holder, container, false);
        ViewHolder viewHolder = new ViewHolder(adapterBinding);
        viewHolder.onBind(imageList.get(position));
        container.addView(adapterBinding.getRoot());
        return adapterBinding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    class ViewHolder {
        private LayoutMovieDetailsViewHolderBinding adapterBinding;

        public ViewHolder(LayoutMovieDetailsViewHolderBinding adapterBinding) {
            this.adapterBinding = adapterBinding;
        }

        public void onBind(Image image) {
            MovieDetailsAdapterViewModel viewModel = new MovieDetailsAdapterViewModel(image);
            adapterBinding.setVariable(BR.viewModel, viewModel);
            adapterBinding.executePendingBindings();
        }
    }
}