package com.shrikantbadwaik.tmdb.domain.usecase.usecaseimpl;

import com.shrikantbadwaik.tmdb.data.remote.apiresponse.ImageResponse;
import com.shrikantbadwaik.tmdb.data.repository.Repository;
import com.shrikantbadwaik.tmdb.domain.usecase.ApiUseCase02;

import javax.inject.Inject;

import retrofit2.Call;

public class MoviePoster extends ApiUseCase02<String, ImageResponse> {
    private final Repository repository;

    @Inject
    public MoviePoster(Repository repository) {
        this.repository = repository;
    }

    @Override
    protected Call<ImageResponse> buildObservableUseCase(String input) {
        return repository.moviePoster(input);
    }
}