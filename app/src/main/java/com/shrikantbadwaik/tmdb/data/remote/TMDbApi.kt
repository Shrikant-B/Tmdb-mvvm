package com.shrikantbadwaik.tmdb.data.remote

import com.shrikantbadwaik.tmdb.data.remote.apiresponse.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDbApi {
    @GET("movie/upcoming")
    fun upcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Observable<MovieResponse>
}