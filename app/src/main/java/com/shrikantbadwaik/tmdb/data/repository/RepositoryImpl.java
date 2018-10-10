package com.shrikantbadwaik.tmdb.data.repository;

import com.shrikantbadwaik.tmdb.data.DataSource;
import com.shrikantbadwaik.tmdb.data.remote.apiresonse.MovieResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;

@Singleton
public class RepositoryImpl implements Repository {
    private final DataSource dataSource;

    @Inject
    public RepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Call<MovieResponse> upcomingMovies() {
        return dataSource.upcomingMovies();
    }
}