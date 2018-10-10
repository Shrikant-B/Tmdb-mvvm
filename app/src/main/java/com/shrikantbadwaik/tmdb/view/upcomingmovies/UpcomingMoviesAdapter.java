package com.shrikantbadwaik.tmdb.view.upcomingmovies;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.shrikantbadwaik.tmdb.BR;
import com.shrikantbadwaik.tmdb.R;
import com.shrikantbadwaik.tmdb.data.model.Movie;
import com.shrikantbadwaik.tmdb.databinding.LayoutUpcomingMoviesViewHolderBinding;
import com.shrikantbadwaik.tmdb.view.base.BaseRecyclerViewHolder;
import com.shrikantbadwaik.tmdb.viewmodel.UpcomingMoviesItemViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UpcomingMoviesAdapter extends RecyclerView.Adapter<BaseRecyclerViewHolder<Movie>> {
    private List<Movie> movieList;

    @Inject
    public UpcomingMoviesAdapter() {
        movieList = new ArrayList<>();
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList.addAll(movieList);
    }

    public void clear() {
        movieList.clear();
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder<Movie> onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutUpcomingMoviesViewHolderBinding adapterBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.layout_upcoming_movies_view_holder, viewGroup, false);
        return new ViewHolder(adapterBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder<Movie> viewHolder, int position) {
        Movie movie = movieList.get(position);
        if (movie != null) viewHolder.onBind(movie, position);
    }

    @Override
    public int getItemCount() {
        return movieList != null && !movieList.isEmpty() ? movieList.size() : 0;
    }

    public class ViewHolder extends BaseRecyclerViewHolder<Movie> {
        private LayoutUpcomingMoviesViewHolderBinding adapterBinding;

        public ViewHolder(@NonNull LayoutUpcomingMoviesViewHolderBinding adapterBinding) {
            super(adapterBinding.getRoot());
            this.adapterBinding = adapterBinding;
        }

        @Override
        public void onBind(Movie movie, int position) {
            UpcomingMoviesItemViewModel viewModel = new UpcomingMoviesItemViewModel(movie);
            adapterBinding.setVariable(BR.viewModel, viewModel);
            adapterBinding.executePendingBindings();
        }
    }
}