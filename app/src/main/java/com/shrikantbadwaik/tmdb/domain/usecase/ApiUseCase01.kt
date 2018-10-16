package com.shrikantbadwaik.tmdb.domain.usecase

import com.shrikantbadwaik.tmdb.data.remote.CallbackObserverWrapper
import com.shrikantbadwaik.tmdb.domain.helper.rx.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

abstract class ApiUseCase01<RESULT>(
    private val schedulerProvider: SchedulerProvider,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) {

    fun execute(callbackWrapper: CallbackObserverWrapper<RESULT>) {
        val disposable: DisposableObserver<RESULT> = prepareObservableUseCase().subscribeWith(callbackWrapper)
        compositeDisposable.add(disposable)
    }

    private fun prepareObservableUseCase(): Observable<RESULT> {
        return buildObservableUseCase().subscribeOn(schedulerProvider.io).observeOn(schedulerProvider.ui)
    }

    protected abstract fun buildObservableUseCase(): Observable<RESULT>
}