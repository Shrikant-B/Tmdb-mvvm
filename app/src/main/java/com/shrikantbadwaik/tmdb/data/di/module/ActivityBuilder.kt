package com.shrikantbadwaik.tmdb.data.di.module

import com.shrikantbadwaik.tmdb.data.di.scope.ActivityScope
import com.shrikantbadwaik.tmdb.view.movies.MoviesActivity
import com.shrikantbadwaik.tmdb.view.movies.MoviesActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MoviesActivityModule::class])
    abstract fun bindMoviesActivity(): MoviesActivity
}