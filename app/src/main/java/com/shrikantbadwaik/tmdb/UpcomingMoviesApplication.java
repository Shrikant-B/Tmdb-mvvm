package com.shrikantbadwaik.tmdb;

import android.app.Activity;
import android.app.Application;

import com.shrikantbadwaik.tmdb.data.di.ApplicationInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class UpcomingMoviesApplication extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> androidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationInjector.inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return androidInjector;
    }
}