package com.shrikantbadwaik.tmdb.domain.usecase.usecaseimpl;

import com.shrikantbadwaik.tmdb.data.remote.apiresponse.MovieResponse;
import com.shrikantbadwaik.tmdb.data.repository.Repository;
import com.shrikantbadwaik.tmdb.domain.helper.rx.SchedulerProvider;
import com.shrikantbadwaik.tmdb.domain.usecase.ApiUseCase01;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UpcomingMovies extends ApiUseCase01<MovieResponse> {
    private final Repository repository;

    @Inject
    public UpcomingMovies(SchedulerProvider schedulerProvider, Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    protected Observable<MovieResponse> buildObservableUseCase() {
        return repository.upcomingMovies();
    }
}