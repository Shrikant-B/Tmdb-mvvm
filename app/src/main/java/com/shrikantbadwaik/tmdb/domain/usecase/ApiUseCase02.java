package com.shrikantbadwaik.tmdb.domain.usecase;

import com.shrikantbadwaik.tmdb.data.remote.CallbackWrapper;

import retrofit2.Call;

public abstract class ApiUseCase02<IN, OUT> {

    public ApiUseCase02() {
    }

    public void execute(IN input, CallbackWrapper<OUT> callbackWrapper) {
        prepareObservableUseCase(input).enqueue(callbackWrapper);
    }

    private Call<OUT> prepareObservableUseCase(IN input) {
        return buildObservableUseCase(input);
    }

    protected abstract Call<OUT> buildObservableUseCase(IN input);
}