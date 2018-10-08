package com.shrikantbadwaik.tmdb.domain.helper;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shrikantbadwaik.tmdb.data.model.Movie;
import com.shrikantbadwaik.tmdb.view.upcomingmovies.UpcomingMoviesAdapter;

import java.util.List;

public final class BindingHelper {
    private BindingHelper() {
    }

    @BindingAdapter("adapter")
    public static void addMovieItems(RecyclerView recyclerView, List<Movie> movieList) {
        UpcomingMoviesAdapter adapter = (UpcomingMoviesAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clear();
            adapter.setMovieList(movieList);
            adapter.notifyDataSetChanged();
        }
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }
}