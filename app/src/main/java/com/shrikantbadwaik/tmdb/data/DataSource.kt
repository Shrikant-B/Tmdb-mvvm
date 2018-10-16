package com.shrikantbadwaik.tmdb.data

import com.shrikantbadwaik.tmdb.data.remote.TMDbApi
import com.shrikantbadwaik.tmdb.data.remote.apiresponse.MovieResponse
import com.shrikantbadwaik.tmdb.data.repository.Repository
import com.shrikantbadwaik.tmdb.domain.Constants
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataSource @Inject constructor(
    private val tmDbApi: TMDbApi
) : Repository {

    override fun getUpcomingMovies(): Observable<MovieResponse> {
        return tmDbApi.upcomingMovies(Constants.API_KEY, Locale.getDefault().language)
    }
}