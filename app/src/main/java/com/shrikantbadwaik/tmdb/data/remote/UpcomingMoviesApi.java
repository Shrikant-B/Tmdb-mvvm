package com.shrikantbadwaik.tmdb.data.remote;

import com.shrikantbadwaik.tmdb.data.remote.apiresponse.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UpcomingMoviesApi {
    @GET("movie/upcoming")
    Call<MovieResponse> upcomingMovies(@Query("api_key") String apiKey);
}