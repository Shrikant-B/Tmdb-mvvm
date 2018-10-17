package com.shrikantbadwaik.tmdb.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableFloat
import android.os.Bundle
import com.shrikantbadwaik.tmdb.data.model.Image
import com.shrikantbadwaik.tmdb.data.model.Movie
import com.shrikantbadwaik.tmdb.data.remote.CallbackObserverWrapper
import com.shrikantbadwaik.tmdb.data.remote.apiresponse.ImageResponse
import com.shrikantbadwaik.tmdb.data.repository.Repository
import com.shrikantbadwaik.tmdb.domain.Constants
import com.shrikantbadwaik.tmdb.domain.usecase.usecaseimpl.MoviePosters
import com.shrikantbadwaik.tmdb.view.base.BaseViewModel
import com.shrikantbadwaik.tmdb.view.moviedetails.MovieDetailsView
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import javax.inject.Inject

class MovieDetailsActivityViewModel @Inject constructor(
    repository: Repository,
    private val moviePosters: MoviePosters
) : BaseViewModel<MovieDetailsView>(repository) {
    private val imageListLiveData: MutableLiveData<MutableList<Image>> = MutableLiveData()
    private val imageListObservable = ObservableArrayList<Image>()
    private val title = ObservableField<String>()
    private val overview = ObservableField<String>()
    private val rating = ObservableFloat()

    fun addImagesToObservable(imageList: MutableList<Image>?) {
        imageList?.let { it ->
            imageListObservable.clear()
            imageListObservable.addAll(it)
        }
    }

    fun getImageListLiveData() = imageListLiveData

    fun getImageListObservable() = imageListObservable

    fun getTitle() = title

    fun getOverview() = overview

    fun getRating() = rating

    fun intentExtras(extras: Bundle?) {
        extras?.let { bundle ->
            val movie: Movie? = bundle.getParcelable(Constants.INTENT_EXTRAS_MOVIE)
            movie?.let { movieDetails ->
                movieDetails.id?.let { movieId -> getMoviePosters(movieId) }
                title.set(movieDetails.title)
                overview.set(movieDetails.overview)
                val df = DecimalFormat("#.#")
                val dfs = DecimalFormatSymbols()
                dfs.decimalSeparator = '.'
                df.decimalFormatSymbols = dfs
                rating.set(df.format((movie.voteAverage / 10 * 5).toDouble()).toFloat())
            }
        }
    }

    private fun getMoviePosters(movieId: String) {
        if (getView()?.isDeviceOnline() == true) {
            setLoading(true)
            moviePosters.execute(movieId, object : CallbackObserverWrapper<ImageResponse>(getView()) {
                override fun onSuccess(result: ImageResponse) {
                    setLoading(false)
                    if (!result.posterList.orEmpty().isEmpty()) {
                        imageListLiveData.value = result.posterList
                    }
                }

                override fun onFailure(error: String) {
                    setLoading(false)
                    getView()?.showErrorMessage(error)
                }
            })
        } else getView()?.showDeviceOfflineError()
    }
}