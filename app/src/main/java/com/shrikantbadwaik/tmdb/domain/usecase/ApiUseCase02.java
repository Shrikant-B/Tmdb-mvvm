package com.shrikantbadwaik.tmdb.domain.usecase;

import com.shrikantbadwaik.tmdb.data.remote.CallbackObserverWrapper;
import com.shrikantbadwaik.tmdb.domain.helper.rx.SchedulerProvider;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public abstract class ApiUseCase02<IN, OUT> {
    private final CompositeDisposable compositeDisposable;
    private final SchedulerProvider schedulerProvider;

    public ApiUseCase02(SchedulerProvider schedulerProvider) {
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = new CompositeDisposable();
    }

    public void execute(IN input, CallbackObserverWrapper<OUT> callbackObserverWrapper) {
        DisposableObserver<OUT> disposable = prepareObservableUseCase(input).subscribeWith(callbackObserverWrapper);
        compositeDisposable.add(disposable);
    }

    private Observable<OUT> prepareObservableUseCase(IN input) {
        return buildObservableUseCase(input).subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui());
    }

    protected abstract Observable<OUT> buildObservableUseCase(IN input);
}