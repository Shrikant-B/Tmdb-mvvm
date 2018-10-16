package com.shrikantbadwaik.tmdb.data.di.module

import com.shrikantbadwaik.tmdb.data.repository.Repository
import com.shrikantbadwaik.tmdb.data.repository.RepositoryImpl
import com.shrikantbadwaik.tmdb.domain.helper.rx.SchedulerProvider
import com.shrikantbadwaik.tmdb.domain.helper.rx.SchedulerProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, ViewModelBuilder::class])
class ApplicationModule {
    @Provides
    @Singleton
    fun repository(repository: RepositoryImpl): Repository = repository

    @Provides
    @Singleton
    fun schedulerProvider(): SchedulerProvider = SchedulerProviderImpl()
}