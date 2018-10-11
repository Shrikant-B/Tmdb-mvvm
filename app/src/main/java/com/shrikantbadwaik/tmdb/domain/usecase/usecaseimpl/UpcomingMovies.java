package com.shrikantbadwaik.tmdb.domain.usecase.usecaseimpl;

import com.shrikantbadwaik.tmdb.data.remote.apiresponse.MovieResponse;
import com.shrikantbadwaik.tmdb.data.repository.Repository;
import com.shrikantbadwaik.tmdb.domain.usecase.ApiUseCase01;

import javax.inject.Inject;

import retrofit2.Call;

public class UpcomingMovies extends ApiUseCase01<MovieResponse> {
    private final Repository repository;

    @Inject
    public UpcomingMovies(Repository repository) {
        this.repository = repository;
    }

    @Override
    protected Call<MovieResponse> buildObservableUseCase() {
        return repository.upcomingMovies();
    }
}