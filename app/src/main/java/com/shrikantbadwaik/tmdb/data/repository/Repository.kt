package com.shrikantbadwaik.tmdb.data.repository

import com.shrikantbadwaik.tmdb.data.remote.apiresponse.MovieResponse
import io.reactivex.Observable

interface Repository {
    fun getUpcomingMovies(): Observable<MovieResponse>
}