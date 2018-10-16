package com.shrikantbadwaik.tmdb.view.movies

import android.app.Activity
import com.shrikantbadwaik.tmdb.data.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class MoviesActivityModule {
    @Provides
    @ActivityScope
    fun provideActivity(activity: MoviesActivity): Activity {
        return activity
    }

    @Provides
    fun provideMoviesAdapter(activity: Activity): MoviesRecyclerAdapter {
        return MoviesRecyclerAdapter(activity.resources.configuration.orientation)
    }
}