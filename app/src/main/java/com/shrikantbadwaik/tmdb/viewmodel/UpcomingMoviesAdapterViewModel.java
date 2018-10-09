package com.shrikantbadwaik.tmdb.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.shrikantbadwaik.tmdb.data.model.Movie;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class UpcomingMoviesAdapterViewModel extends BaseObservable {
    private final ObservableField<String> title;
    private final ObservableField<String> certificate;
    private final ObservableField<String> releaseDate;
    private final ObservableField<String> rating;
    private final ObservableField<String> imageUrl;
    private final Movie movie;
    private Callback callback;

    public UpcomingMoviesAdapterViewModel(Movie movie, Callback callback) {
        this.movie = movie;
        this.callback = callback;
        title = new ObservableField<>(movie.getTitle());
        certificate = new ObservableField<>(movie.isAdult() ? "A" : "U/A");
        releaseDate = new ObservableField<>(movie.getReleaseDate());
        DecimalFormat decimalFormat = new DecimalFormat("★#.#");
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        rating = new ObservableField<>(decimalFormat.format((movie.getVoteAverage() / 10) * 5));
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

    public void onItemClicked() {
        callback.onItemClicked(movie);
    }

    public interface Callback {
        void onItemClicked(Movie movie);
    }
}