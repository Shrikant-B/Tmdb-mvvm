package com.shrikantbadwaik.tmdb.data.repository;

import com.shrikantbadwaik.tmdb.data.remote.apiresponse.ImageResponse;
import com.shrikantbadwaik.tmdb.data.remote.apiresponse.MovieResponse;

import io.reactivex.Observable;

public interface Repository {
    Observable<MovieResponse> upcomingMovies();

    Observable<ImageResponse> moviePosters(String id);
}