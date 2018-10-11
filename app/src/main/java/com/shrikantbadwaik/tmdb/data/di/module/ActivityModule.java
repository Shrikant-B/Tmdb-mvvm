package com.shrikantbadwaik.tmdb.data.di.module;

import com.shrikantbadwaik.tmdb.data.di.annotation.ActivityScope;
import com.shrikantbadwaik.tmdb.view.moviedetails.MovieDetailsActivity;
import com.shrikantbadwaik.tmdb.view.upcomingmovies.UpcomingMoviesActivity;
import com.shrikantbadwaik.tmdb.view.upcomingmovies.UpcomingMoviesModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = UpcomingMoviesModule.class)
    public abstract UpcomingMoviesActivity upcomingMoviesActivity();

    @ActivityScope
    @ContributesAndroidInjector
    public abstract MovieDetailsActivity movieDetailsActivity();
}