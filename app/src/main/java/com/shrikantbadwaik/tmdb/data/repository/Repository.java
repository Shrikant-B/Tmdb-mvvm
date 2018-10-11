package com.shrikantbadwaik.tmdb.data.repository;

import com.shrikantbadwaik.tmdb.data.remote.apiresponse.MovieResponse;

import retrofit2.Call;

public interface Repository {
    Call<MovieResponse> upcomingMovies();
}