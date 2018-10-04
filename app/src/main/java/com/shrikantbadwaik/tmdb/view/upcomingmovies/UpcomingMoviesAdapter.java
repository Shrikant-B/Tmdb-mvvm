package com.shrikantbadwaik.tmdb.view.upcomingmovies;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.shrikantbadwaik.tmdb.R;
import com.shrikantbadwaik.tmdb.data.model.Movie;
import com.shrikantbadwaik.tmdb.databinding.LayoutUpcomingMoviesViewHolderBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UpcomingMoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Movie> movieList;

    @Inject
    public UpcomingMoviesAdapter() {
        movieList = new ArrayList<>();
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList.clear();
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutUpcomingMoviesViewHolderBinding adapterBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.layout_upcoming_movies_view_holder, viewGroup, false);
        return new ViewHolder(adapterBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        Movie movie = movieList.get(position);
        if (movie != null) {
            holder.adapterBinding.layoutUpcomingMovieViewHolderTvName.setText(movie.getTitle());
            Glide.with(holder.itemView)
                    .load(movie.getPosterPath())
                    .into(holder.adapterBinding.layoutUpcomingMovieViewHolderIvPoster);
        }
    }

    @Override
    public int getItemCount() {
        return movieList != null && !movieList.isEmpty() ? movieList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LayoutUpcomingMoviesViewHolderBinding adapterBinding;

        public ViewHolder(@NonNull LayoutUpcomingMoviesViewHolderBinding adapterBinding) {
            super(adapterBinding.getRoot());
            this.adapterBinding = adapterBinding;
        }
    }
}