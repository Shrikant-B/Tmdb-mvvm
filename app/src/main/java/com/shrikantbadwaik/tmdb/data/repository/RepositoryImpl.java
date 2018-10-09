package com.shrikantbadwaik.tmdb.data.repository;

import com.shrikantbadwaik.tmdb.data.DataSource;
import com.shrikantbadwaik.tmdb.data.remote.apiresponse.ImageResponse;
import com.shrikantbadwaik.tmdb.data.remote.apiresponse.MovieResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class RepositoryImpl implements Repository {
    private DataSource dataSource;

    @Inject
    public RepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Observable<MovieResponse> upcomingMovies() {
        return dataSource.upcomingMovies();
    }

    @Override
    public Observable<ImageResponse> moviePosters(String id) {
        return dataSource.moviePosters(id);
    }
}