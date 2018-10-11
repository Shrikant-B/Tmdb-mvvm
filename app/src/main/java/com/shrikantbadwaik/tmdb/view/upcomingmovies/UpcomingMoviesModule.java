package com.shrikantbadwaik.tmdb.view.upcomingmovies;

import android.app.Activity;

import com.shrikantbadwaik.tmdb.data.di.annotation.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class UpcomingMoviesModule {
    @Provides
    @ActivityScope
    public Activity provideActivity(UpcomingMoviesActivity activity) {
        return activity;
    }

    @Provides
    public UpcomingMoviesAdapter provideUpcomingMoviesAdapter(Activity activity) {
        return new UpcomingMoviesAdapter(activity.getResources().getConfiguration().orientation);
    }
}