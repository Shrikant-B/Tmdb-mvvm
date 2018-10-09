package com.shrikantbadwaik.tmdb.domain.usecase.usecaseimpl;

import com.shrikantbadwaik.tmdb.data.remote.apiresponse.ImageResponse;
import com.shrikantbadwaik.tmdb.data.repository.Repository;
import com.shrikantbadwaik.tmdb.domain.helper.rx.SchedulerProvider;
import com.shrikantbadwaik.tmdb.domain.usecase.ApiUseCase02;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MoviePoster extends ApiUseCase02<String, ImageResponse> {
    private final Repository repository;

    @Inject
    public MoviePoster(SchedulerProvider schedulerProvider, Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    protected Observable<ImageResponse> buildObservableUseCase(String input) {
        return repository.moviePosters(input);
    }
}