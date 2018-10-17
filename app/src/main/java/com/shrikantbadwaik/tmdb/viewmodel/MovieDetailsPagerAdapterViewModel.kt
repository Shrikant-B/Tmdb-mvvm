package com.shrikantbadwaik.tmdb.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableField
import com.shrikantbadwaik.tmdb.data.model.Image

class MovieDetailsPagerAdapterViewModel(image: Image) : BaseObservable() {
    private val imageUrl: ObservableField<String> = ObservableField()

    init {
        imageUrl.set(image.filePath())
    }

    fun getImageUrl(): ObservableField<String> = imageUrl
}