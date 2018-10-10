package com.shrikantbadwaik.tmdb.data.remote;

import android.support.annotation.NonNull;

import com.shrikantbadwaik.tmdb.domain.helper.Constants;
import com.shrikantbadwaik.tmdb.view.base.BaseView;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public abstract class CallbackWrapper<T> implements Callback<T> {
    private final WeakReference<BaseView> weakReference;

    protected CallbackWrapper(BaseView view) {
        this.weakReference = new WeakReference<>(view);
    }

    protected abstract void onSuccess(T result);

    protected abstract void onError(String error);

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        BaseView view = weakReference.get();
        if (view != null) {
            onSuccess(response.body());
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable e) {
        BaseView view = weakReference.get();
        if (view != null) {
            if (e instanceof HttpException) {
                int errorCode = ((HttpException) e).response().code();
                if (errorCode == 503 || errorCode == 500) {
                    String errorMessage = e.getLocalizedMessage();
                    onError(errorMessage);
                } else {
                    ResponseBody responseBody = ((HttpException) e).response().errorBody();
                    onError(getErrorMessage(responseBody));
                }
            } else if (e instanceof SocketTimeoutException) {
                view.showSocketException();
            } else if (e instanceof IOException) {
                view.showIOException();
            } else onError(e.getMessage());
        }
    }

    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString(Constants.API_ERROR_RESPONSE_KEY);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}