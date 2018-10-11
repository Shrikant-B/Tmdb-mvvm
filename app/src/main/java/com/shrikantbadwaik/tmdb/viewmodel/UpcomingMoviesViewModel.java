package com.shrikantbadwaik.tmdb.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.shrikantbadwaik.tmdb.data.model.Movie;
import com.shrikantbadwaik.tmdb.data.remote.CallbackWrapper;
import com.shrikantbadwaik.tmdb.data.remote.apiresponse.MovieResponse;
import com.shrikantbadwaik.tmdb.data.repository.Repository;
import com.shrikantbadwaik.tmdb.domain.usecase.usecaseimpl.UpcomingMovies;
import com.shrikantbadwaik.tmdb.view.base.BaseViewModel;
import com.shrikantbadwaik.tmdb.view.upcomingmovies.UpcomingMoviesView;

import java.util.List;

import javax.inject.Inject;

public class UpcomingMoviesViewModel extends BaseViewModel<UpcomingMoviesView> {
    private final UpcomingMovies upcomingMovies;
    private final ObservableList<Movie> moviesObservable;
    private final MutableLiveData<List<Movie>> moviesLiveData;

    @Inject
    public UpcomingMoviesViewModel(Repository repository, UpcomingMovies upcomingMovies) {
        super(repository);
        this.upcomingMovies = upcomingMovies;
        moviesObservable = new ObservableArrayList<>();
        moviesLiveData = new MutableLiveData<>();
    }

    public void getUpcomingMovies() {
        if (getView().isDeviceOnline()) {
            setLoading(true);
            upcomingMovies.execute(new CallbackWrapper<MovieResponse>(getView()) {
                @Override
                protected void onSuccess(MovieResponse movieResponse) {
                    if (isViewAttached()) {
                        setLoading(false);
                        if (movieResponse != null) {
                            List<Movie> movieList = movieResponse.getResults();
                            if (movieList != null && !movieList.isEmpty()) {
                                moviesLiveData.setValue(movieList);
                            }
                        }
                    }
                }

                @Override
                protected void onError(String error) {
                    if (isViewAttached()) {
                        setLoading(false);
                        getView().showErrorMessage(error);
                    }
                }
            });
        } else getView().showDeviceOfflineError();
    }

    public void addMovieListToObservable(List<Movie> movieList) {
        moviesObservable.clear();
        moviesObservable.addAll(movieList);
    }

    public ObservableList<Movie> getMoviesObservable() {
        return moviesObservable;
    }

    public MutableLiveData<List<Movie>> getMovieListLiveData() {
        return moviesLiveData;
    }
}