package com.shrikantbadwaik.tmdb.domain.usecase;

import com.shrikantbadwaik.tmdb.data.remote.CallbackWrapper;

import retrofit2.Call;

public abstract class ApiUseCase01<OUT> {

    public ApiUseCase01() {
    }

    public void execute(CallbackWrapper<OUT> callbackWrapper) {
        prepareObservableUseCase().enqueue(callbackWrapper);
    }

    private Call<OUT> prepareObservableUseCase() {
        return buildObservableUseCase();
    }

    protected abstract Call<OUT> buildObservableUseCase();
}