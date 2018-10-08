package com.shrikantbadwaik.tmdb.data.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.shrikantbadwaik.tmdb.data.di.annotation.ViewModelKey;
import com.shrikantbadwaik.tmdb.domain.helper.ViewModelProviderFactory;
import com.shrikantbadwaik.tmdb.viewmodel.UpcomingMoviesViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UpcomingMoviesViewModel.class)
    abstract ViewModel upcomingMoviesViewModel(UpcomingMoviesViewModel upcomingMoviesViewModel);

    @Binds
    abstract ViewModelProvider.Factory viewModelProviderFactory(ViewModelProviderFactory viewModelProviderFactory);
}