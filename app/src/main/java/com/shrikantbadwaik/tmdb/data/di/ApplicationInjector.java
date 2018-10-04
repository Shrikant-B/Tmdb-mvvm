package com.shrikantbadwaik.tmdb.data.di;

import com.shrikantbadwaik.tmdb.UpcomingMoviesApplication;
import com.shrikantbadwaik.tmdb.data.di.component.ApplicationComponent;
import com.shrikantbadwaik.tmdb.data.di.component.DaggerApplicationComponent;

public class ApplicationInjector {
    public static void inject(UpcomingMoviesApplication upcomingMoviesApplication) {
        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .application(upcomingMoviesApplication)
                .build();
        applicationComponent.inject(upcomingMoviesApplication);
    }
}