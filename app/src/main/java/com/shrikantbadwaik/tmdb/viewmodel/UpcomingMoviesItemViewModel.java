package com.shrikantbadwaik.tmdb.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.shrikantbadwaik.tmdb.data.model.Movie;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class UpcomingMoviesItemViewModel extends BaseObservable {
    private final ObservableField<String> imageUrl;
    private final ObservableField<String> title;
    private final ObservableField<String> certificate;
    private final ObservableField<String> releaseDate;
    private final ObservableField<String> rating;
    private Movie movie;

    public UpcomingMoviesItemViewModel(Movie movie) {
        this.movie = movie;
        title = new ObservableField<>(movie.getTitle());
        imageUrl = new ObservableField<>(movie.getPosterPath());
        certificate = new ObservableField<>(movie.isAdult() ? "A" : "U/A");
        releaseDate = new ObservableField<>(movie.getReleaseDate());
        DecimalFormat decimalFormat = new DecimalFormat("★#.#");
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        rating = new ObservableField<>(decimalFormat.format((movie.getVoteAverage() / 10) * 5));
    }

    public ObservableField<String> getImageUrl() {
        return imageUrl;
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
}