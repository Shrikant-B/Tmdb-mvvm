package com.shrikantbadwaik.tmdb.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.shrikantbadwaik.tmdb.data.model.Movie;

public class UpcomingMoviesAdapterViewModel extends BaseObservable {
    private final ObservableField<String> title;
    private final ObservableField<String> certificate;
    private final ObservableField<String> releaseDate;
    private final ObservableField<String> rating;
    private final ObservableField<String> imageUrl;
    private final Movie movie;

    public UpcomingMoviesAdapterViewModel(Movie movie) {
        this.movie = movie;
        title = new ObservableField<>(movie.getTitle());
        certificate = new ObservableField<>(movie.isAdult() ? "A" : "U/A");
        releaseDate = new ObservableField<>(movie.getReleaseDate());
        rating = new ObservableField<>(String.format("★%s", movie.getVoteAverage()));
        imageUrl = new ObservableField<>(movie.getPosterPath());
    }

    public ObservableField<String> getTitle() {
        return title;
    }

    public ObservableField<String> getCertificate() {
        return certificate;
    }

    public ObservableField<String> getReleaseDate() {
        return releaseDate;
    }

    public ObservableField<String> getRating() {
        return rating;
    }

    public ObservableField<String> getImageUrl() {
        return imageUrl;
    }
}