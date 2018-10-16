package com.shrikantbadwaik.tmdb.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableField
import com.shrikantbadwaik.tmdb.data.model.Movie
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class MoviesRecyclerAdapterViewModel(
    private val movie: Movie, private val callback: Callback
) : BaseObservable() {
    private val imageUrl: ObservableField<String> = ObservableField()
    private val title: ObservableField<String> = ObservableField()
    private val certificate: ObservableField<String> = ObservableField()
    private val releaseDate: ObservableField<String> = ObservableField()
    private val rating: ObservableField<String> = ObservableField()

    init {
        imageUrl.set(movie.posterPath())
        title.set(movie.title)
        certificate.set(if (movie.adult) "A" else "U/A")
        releaseDate.set(movie.releaseDate)
        val df = DecimalFormat("★#.#")
        val dfs = DecimalFormatSymbols()
        dfs.decimalSeparator = '.'
        df.decimalFormatSymbols = dfs
        rating.set(df.format((movie.voteAverage / 10 * 5).toDouble()))
    }

    fun getImageUrl(): ObservableField<String> = imageUrl

    fun getTitle(): ObservableField<String> = title

    fun getCertificate(): ObservableField<String> = certificate

    fun getReleaseDate(): ObservableField<String> = releaseDate

    fun getRating(): ObservableField<String> = rating

    fun onItemClicked() {
        callback.onItemClicked(movie)
    }

    interface Callback {
        fun onItemClicked(movie: Movie)
    }
}