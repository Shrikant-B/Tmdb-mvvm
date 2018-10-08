package com.shrikantbadwaik.tmdb.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.shrikantbadwaik.tmdb.data.model.Movie;
import com.shrikantbadwaik.tmdb.data.model.MovieResponse;
import com.shrikantbadwaik.tmdb.data.remote.CallbackObserverWrapper;
import com.shrikantbadwaik.tmdb.data.repository.Repository;
import com.shrikantbadwaik.tmdb.domain.usecase.usecaseimpl.UpcomingMovies;
import com.shrikantbadwaik.tmdb.view.base.BaseViewModel;
import com.shrikantbadwaik.tmdb.view.upcomingmovies.UpcomingMoviesView;

import java.util.List;

import javax.inject.Inject;

public class UpcomingMoviesViewModel extends BaseViewModel<UpcomingMoviesView> {
    private final UpcomingMovies upcomingMovies;
    private ObservableList<Movie> movieObservableList = new ObservableArrayList<>();

    @Inject
    public UpcomingMoviesViewModel(Repository repository, UpcomingMovies upcomingMovies) {
        super(repository);
        this.upcomingMovies = upcomingMovies;
    }

    public void getUpcomingMovies() {
        if (getView().isDeviceOnline()) {
            setLoading(true);
            upcomingMovies.execute(new CallbackObserverWrapper<MovieResponse>(getView()) {
                @Override
                protected void onSuccess(MovieResponse movieResponse) {
                    if (isViewAttached()) {
                        setLoading(false);
                        if (movieResponse != null) {
                            List<Movie> movieList = movieResponse.getResults();
                            if (movieList != null && !movieList.isEmpty()) {
                                movieObservableList.clear();
                                movieObservableList.addAll(movieList);
                            }
                        }
                    }
                }

                @Override
                protected void onFailure(String error) {
                    if (isViewAttached()) {
                        setLoading(false);
                    }
                }
            });
        } else getView().showDeviceOfflineError();
    }

    public ObservableList<Movie> getMovieObservableList() {
        return movieObservableList;
    }
}