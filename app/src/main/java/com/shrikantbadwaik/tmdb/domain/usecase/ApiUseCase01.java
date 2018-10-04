package com.shrikantbadwaik.tmdb.domain.usecase;

import com.shrikantbadwaik.tmdb.data.remote.CallbackObserverWrapper;
import com.shrikantbadwaik.tmdb.domain.helper.rx.SchedulerProvider;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public abstract class ApiUseCase01<OUT> {
    private final CompositeDisposable compositeDisposable;
    private final SchedulerProvider schedulerProvider;

    public ApiUseCase01(SchedulerProvider schedulerProvider) {
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = new CompositeDisposable();
    }

    public void execute(CallbackObserverWrapper<OUT> callbackObserverWrapper) {
        DisposableObserver<OUT> disposable = prepareObservableUseCase().subscribeWith(callbackObserverWrapper);
        compositeDisposable.add(disposable);
    }

    private Observable<OUT> prepareObservableUseCase() {
        return buildObservableUseCase().subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui());
    }

    protected abstract Observable<OUT> buildObservableUseCase();
}