package com.shrikantbadwaik.tmdb;

import android.app.Activity;
import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.shrikantbadwaik.tmdb.data.di.ApplicationInjector;

import io.fabric.sdk.android.Fabric;
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
        Fabric.with(this, new Crashlytics());
        ApplicationInjector.inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return androidInjector;
    }
}