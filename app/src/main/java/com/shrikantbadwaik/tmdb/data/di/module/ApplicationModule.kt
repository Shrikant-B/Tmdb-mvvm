package com.shrikantbadwaik.tmdb.data.di.module

import com.shrikantbadwaik.tmdb.data.repository.Repository
import com.shrikantbadwaik.tmdb.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, ViewModelBuilder::class])
class ApplicationModule {
    @Provides
    @Singleton
    fun repository(repository: RepositoryImpl): Repository = repository
}