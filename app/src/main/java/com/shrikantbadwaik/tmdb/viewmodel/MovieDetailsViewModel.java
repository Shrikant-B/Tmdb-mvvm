package com.shrikantbadwaik.tmdb.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableList;
import android.os.Bundle;

import com.shrikantbadwaik.tmdb.data.model.Image;
import com.shrikantbadwaik.tmdb.data.model.Movie;
import com.shrikantbadwaik.tmdb.data.remote.CallbackObserverWrapper;
import com.shrikantbadwaik.tmdb.data.remote.apiresponse.ImageResponse;
import com.shrikantbadwaik.tmdb.data.repository.Repository;
import com.shrikantbadwaik.tmdb.domain.helper.Constants;
import com.shrikantbadwaik.tmdb.domain.usecase.usecaseimpl.MoviePoster;
import com.shrikantbadwaik.tmdb.view.base.BaseViewModel;
import com.shrikantbadwaik.tmdb.view.moviedetails.MovieDetailsView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import javax.inject.Inject;

public class MovieDetailsViewModel extends BaseViewModel<MovieDetailsView> {
    private final MoviePoster moviePoster;
    private ObservableList<Image> imageObservableList = new ObservableArrayList<>();
    private ObservableField<String> title = new ObservableField<>();
    private ObservableField<String> overview = new ObservableField<>();
    private ObservableFloat rating = new ObservableFloat();

    @Inject
    public MovieDetailsViewModel(Repository repository, MoviePoster moviePoster) {
        super(repository);
        this.moviePoster = moviePoster;
    }

    public void intentExtras(Bundle extras) {
        if (extras != null) {
            Movie movie = extras.getParcelable(Constants.INTENT_EXTRAS_MOVIE);
            if (movie != null) {
                getMoviePosters(movie);
                title.set(movie.getTitle());
                overview.set(movie.getOverview());
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
                decimalFormatSymbols.setDecimalSeparator('.');
                decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
                rating.set(Float.valueOf(decimalFormat.format((movie.getVoteAverage() / 10) * 5)));
            }
        }
    }

    private void getMoviePosters(Movie movie) {
        if (getView().isDeviceOnline()) {
            setLoading(true);
            moviePoster.execute(movie.getId(), new CallbackObserverWrapper<ImageResponse>(getView()) {
                @Override
                protected void onSuccess(ImageResponse imageResponse) {
                    if (isViewAttached()) {
                        setLoading(false);
                        if (imageResponse != null) {
                            List<Image> imageList = imageResponse.getPosterList();
                            if (imageList != null && !imageList.isEmpty()) {
                                imageObservableList.clear();
                                imageObservableList.addAll(imageList);
                            }
                        }
                    }
                }

                @Override
                protected void onFailure(String error) {
                    if (isViewAttached()) {
                        setLoading(false);
                        getView().showErrorMessage(error);
                    }
                }
            });
        } else getView().showDeviceOfflineError();
    }

    public ObservableList<Image> getImageObservableList() {
        return imageObservableList;
    }

    public ObservableField<String> getTitle() {
        return title;
    }

    public ObservableField<String> getOverview() {
        return overview;
    }

    public ObservableFloat getRating() {
        return rating;
    }
}