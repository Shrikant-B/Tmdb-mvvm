package com.shrikantbadwaik.tmdb.data.di.module;

import com.shrikantbadwaik.tmdb.data.di.annotation.ActivityScope;
import com.shrikantbadwaik.tmdb.view.upcomingmovies.UpcomingMoviesActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector
    public abstract UpcomingMoviesActivity upcomingMoviesActivity();
}