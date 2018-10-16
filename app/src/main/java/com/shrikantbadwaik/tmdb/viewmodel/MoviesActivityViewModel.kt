package com.shrikantbadwaik.tmdb.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.shrikantbadwaik.tmdb.data.model.Movie
import com.shrikantbadwaik.tmdb.data.remote.CallbackObserverWrapper
import com.shrikantbadwaik.tmdb.data.remote.apiresponse.MovieResponse
import com.shrikantbadwaik.tmdb.data.repository.Repository
import com.shrikantbadwaik.tmdb.domain.usecase.usecaseimpl.UpcomingMovies
import com.shrikantbadwaik.tmdb.view.base.BaseViewModel
import com.shrikantbadwaik.tmdb.view.movies.MoviesView
import javax.inject.Inject

class MoviesActivityViewModel @Inject constructor(
    repository: Repository,
    private val upcomingMovies: UpcomingMovies
) : BaseViewModel<MoviesView>(repository) {
    private val movieListObservable: ObservableList<Movie> = ObservableArrayList()
    private val movieListLiveData: MutableLiveData<MutableList<Movie>> = MutableLiveData()

    fun getUpcomingMovies() {
        if (getView()?.isDeviceOnline() == true) {
            setLoading(true)
            upcomingMovies.execute(object : CallbackObserverWrapper<MovieResponse>(getView()) {
                override fun onSuccess(result: MovieResponse) {
                    setLoading(false)
                    if (!result.movieList.orEmpty().isEmpty()) {
                        movieListLiveData.value = result.movieList
                    }
                }

                override fun onFailure(error: String) {
                    setLoading(false)
                    getView()?.showErrorMessage(error)
                }
            })
        } else getView()?.showDeviceOfflineError()
    }

    fun addMovieListToObservable(movieList: MutableList<Movie>?) {
        movieList?.let { it ->
            movieListObservable.clear()
            movieListObservable.addAll(it)
        } ?: return
    }

    fun getMovieListObservable() = movieListObservable

    fun getMovieListLiveData() = movieListLiveData
}