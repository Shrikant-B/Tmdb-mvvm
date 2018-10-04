package com.shrikantbadwaik.tmdb.data.di.module;

import com.shrikantbadwaik.tmdb.data.repository.Repository;
import com.shrikantbadwaik.tmdb.data.repository.RepositoryImpl;
import com.shrikantbadwaik.tmdb.domain.helper.rx.AppSchedulerProvider;
import com.shrikantbadwaik.tmdb.domain.helper.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {NetworkModule.class, ViewModelModule.class})
public class ApplicationModule {

    @Provides
    @Singleton
    public Repository provideRepository(RepositoryImpl repository) {
        return repository;
    }

    @Provides
    @Singleton
    SchedulerProvider provideScheduler() {
        return new AppSchedulerProvider();
    }
}