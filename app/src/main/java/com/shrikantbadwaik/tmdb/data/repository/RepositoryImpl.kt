package com.shrikantbadwaik.tmdb.data.repository

import com.shrikantbadwaik.tmdb.data.DataSource
import com.shrikantbadwaik.tmdb.data.remote.apiresponse.ImageResponse
import com.shrikantbadwaik.tmdb.data.remote.apiresponse.MovieResponse
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : Repository {
    override fun getUpcomingMovies(): Observable<MovieResponse> {
        return dataSource.getUpcomingMovies()
    }

    override fun getMoviePosters(movieId: String): Observable<ImageResponse> {
        return dataSource.getMoviePosters(movieId)
    }
}