package com.shrikantbadwaik.tmdb.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.shrikantbadwaik.tmdb.data.model.Image;

public class MovieDetailsAdapterViewModel extends BaseObservable {
    private final ObservableField<String> imageUrl;
    private final Image image;

    public MovieDetailsAdapterViewModel(Image image) {
        this.imageUrl = new ObservableField<>(image.getFilePath());
        this.image = image;
    }

    public ObservableField<String> getImageUrl() {
        return imageUrl;
    }
}