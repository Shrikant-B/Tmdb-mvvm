package com.shrikantbadwaik.tmdb.domain.usecase.usecaseimpl

import com.shrikantbadwaik.tmdb.data.remote.apiresponse.MovieResponse
import com.shrikantbadwaik.tmdb.data.repository.Repository
import com.shrikantbadwaik.tmdb.domain.helper.rx.SchedulerProvider
import com.shrikantbadwaik.tmdb.domain.usecase.ApiUseCase01
import io.reactivex.Observable
import javax.inject.Inject

class UpcomingMovies @Inject constructor(
    schedulerProvider: SchedulerProvider,
    private val repository: Repository
) : ApiUseCase01<MovieResponse>(schedulerProvider) {

    override fun buildObservableUseCase(): Observable<MovieResponse> {
        return repository.getUpcomingMovies()
    }
}