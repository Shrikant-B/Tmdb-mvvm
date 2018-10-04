package com.shrikantbadwaik.tmdb.data.di.component;

import android.app.Application;

import com.shrikantbadwaik.tmdb.UpcomingMoviesApplication;
import com.shrikantbadwaik.tmdb.data.di.module.ActivityModule;
import com.shrikantbadwaik.tmdb.data.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class, AndroidInjectionModule.class,
        ApplicationModule.class, ActivityModule.class})
public interface ApplicationComponent {
    void inject(UpcomingMoviesApplication upcomingMoviesApplication);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ApplicationComponent.Builder application(Application application);

        ApplicationComponent build();
    }
}