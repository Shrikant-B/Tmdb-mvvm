package com.shrikantbadwaik.tmdb.domain.usecase.usecaseimpl

import com.shrikantbadwaik.tmdb.data.remote.apiresponse.ImageResponse
import com.shrikantbadwaik.tmdb.data.repository.Repository
import com.shrikantbadwaik.tmdb.domain.helper.rx.SchedulerProvider
import com.shrikantbadwaik.tmdb.domain.usecase.ApiUseCase02
import io.reactivex.Observable
import javax.inject.Inject

class MoviePosters @Inject constructor(
    schedulerProvider: SchedulerProvider,
    private val repository: Repository
) : ApiUseCase02<String, ImageResponse>(schedulerProvider) {

    override fun buildObservableUseCase(input: String): Observable<ImageResponse> {
        return repository.getMoviePosters(input)
    }
}