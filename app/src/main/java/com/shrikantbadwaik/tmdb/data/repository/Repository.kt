package com.shrikantbadwaik.tmdb.data.repository

import com.shrikantbadwaik.tmdb.data.remote.apiresponse.ImageResponse
import com.shrikantbadwaik.tmdb.data.remote.apiresponse.MovieResponse
import io.reactivex.Observable

interface Repository {
    fun getUpcomingMovies(): Observable<MovieResponse>

    fun getMoviePosters(movieId: String): Observable<ImageResponse>
}