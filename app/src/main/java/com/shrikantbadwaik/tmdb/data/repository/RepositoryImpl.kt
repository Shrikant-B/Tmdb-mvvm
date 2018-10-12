package com.shrikantbadwaik.tmdb.data.repository

import com.shrikantbadwaik.tmdb.data.DataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : Repository {
}