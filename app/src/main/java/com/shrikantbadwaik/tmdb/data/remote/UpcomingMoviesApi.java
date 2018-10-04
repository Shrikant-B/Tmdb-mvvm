package com.shrikantbadwaik.tmdb.data.remote;

import com.shrikantbadwaik.tmdb.data.model.MovieResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UpcomingMoviesApi {
    @GET("movie/upcoming")
    Observable<MovieResponse> upcomingMovies(@Query("api_key") String apiKey);
}