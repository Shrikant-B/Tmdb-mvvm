package com.shrikantbadwaik.tmdb.domain.usecase

import com.shrikantbadwaik.tmdb.data.remote.CallbackObserverWrapper
import com.shrikantbadwaik.tmdb.domain.helper.rx.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

abstract class ApiUseCase02<INPUT, RESULT>(
    private val schedulerProvider: SchedulerProvider,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) {
    fun execute(input: INPUT, callbackWrapper: CallbackObserverWrapper<RESULT>) {
        val disposable: DisposableObserver<RESULT> = prepareObservableUseCase(input).subscribeWith(callbackWrapper)
        compositeDisposable.add(disposable)
    }

    private fun prepareObservableUseCase(input: INPUT): Observable<RESULT> {
        return buildObservableUseCase(input).subscribeOn(schedulerProvider.io).observeOn(schedulerProvider.ui)
    }

    protected abstract fun buildObservableUseCase(input: INPUT): Observable<RESULT>
}