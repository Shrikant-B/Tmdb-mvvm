package com.shrikantbadwaik.tmdb.data.repository;

import com.shrikantbadwaik.tmdb.data.model.MovieResponse;

import io.reactivex.Observable;

public interface Repository {
    Observable<MovieResponse> upcomingMovies();
}