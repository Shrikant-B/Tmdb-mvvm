package com.shrikantbadwaik.tmdb.data.di

import com.shrikantbadwaik.tmdb.UpcomingMoviesApplication
import com.shrikantbadwaik.tmdb.data.di.component.DaggerApplicationComponent

object ApplicationInjector {
    fun inject(upcomingMoviesApplication: UpcomingMoviesApplication) {
        val applicationComponent = DaggerApplicationComponent.builder().application(upcomingMoviesApplication).build()
        applicationComponent.inject(upcomingMoviesApplication)
    }
}