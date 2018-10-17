package com.shrikantbadwaik.tmdb.data.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.shrikantbadwaik.tmdb.data.di.scope.ViewModelKey
import com.shrikantbadwaik.tmdb.domain.helper.ViewModelProviderFactory
import com.shrikantbadwaik.tmdb.viewmodel.MovieDetailsActivityViewModel
import com.shrikantbadwaik.tmdb.viewmodel.MoviesActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(MoviesActivityViewModel::class)
    abstract fun bindMoviesViewModel(viewModel: MoviesActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsActivityViewModel::class)
    abstract fun bindMovieDetailsViewModel(viewModel: MovieDetailsActivityViewModel): ViewModel

    @Binds
    abstract fun bindViewModelProviderFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}