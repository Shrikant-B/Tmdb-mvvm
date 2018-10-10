package com.shrikantbadwaik.tmdb.data.repository;

import com.shrikantbadwaik.tmdb.data.remote.apiresonse.MovieResponse;

import retrofit2.Call;

public interface Repository {
    Call<MovieResponse> upcomingMovies();
}