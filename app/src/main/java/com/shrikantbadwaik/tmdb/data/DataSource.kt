package com.shrikantbadwaik.tmdb.data

import com.shrikantbadwaik.tmdb.data.remote.TMDbApi
import com.shrikantbadwaik.tmdb.data.repository.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataSource @Inject constructor(
    private val tmDbApi: TMDbApi
) : Repository {
}