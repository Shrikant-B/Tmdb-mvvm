package com.shrikantbadwaik.tmdb.data;

import com.shrikantbadwaik.tmdb.data.remote.UpcomingMoviesApi;
import com.shrikantbadwaik.tmdb.data.remote.apiresonse.MovieResponse;
import com.shrikantbadwaik.tmdb.data.repository.Repository;
import com.shrikantbadwaik.tmdb.domain.helper.Constants;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;

@Singleton
public class DataSource implements Repository {
    private final UpcomingMoviesApi upcomingMoviesApi;

    @Inject
    public DataSource(UpcomingMoviesApi upcomingMoviesApi) {
        this.upcomingMoviesApi = upcomingMoviesApi;
    }

    @Override
    public Call<MovieResponse> upcomingMovies() {
        return upcomingMoviesApi.upcomingMovies(Constants.API_KEY);
    }
}