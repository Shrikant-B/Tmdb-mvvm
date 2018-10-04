package com.shrikantbadwaik.tmdb.view.upcomingmovies;

import com.shrikantbadwaik.tmdb.data.model.Movie;
import com.shrikantbadwaik.tmdb.view.base.BaseView;

import java.util.List;

public interface UpcomingMoviesView extends BaseView {
    void showUpcomingMovies(List<Movie> movieList);
}