package com.shrikantbadwaik.tmdb.data.remote;

import com.shrikantbadwaik.tmdb.domain.helper.Constants;
import com.shrikantbadwaik.tmdb.view.base.BaseView;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public abstract class CallbackObserverWrapper<T> extends DisposableObserver<T> {
    //MvpView is just a reference of a View in MVP
    private WeakReference<BaseView> weakReference;

    protected CallbackObserverWrapper(BaseView view) {
        this.weakReference = new WeakReference<>(view);
    }

    protected abstract void onSuccess(T result);

    protected abstract void onFailure(String error);

    protected void onCompleted() {

    }

    @Override
    public void onNext(T result) {
        BaseView view = weakReference.get();
        if (view != null) {
            onSuccess(result);
        }
    }

    @Override
    public void onError(Throwable e) {
        BaseView view = weakReference.get();
        if (view != null) {
            if (e instanceof HttpException) {
                int errorCode = ((HttpException) e).response().code();
                if (errorCode == 503 || errorCode == 500) {
                    String errorMessage = e.getLocalizedMessage();
                    onFailure(errorMessage);
                } else {
                    ResponseBody responseBody = ((HttpException) e).response().errorBody();
                    onFailure(getErrorMessage(responseBody));
                }
            } else if (e instanceof SocketTimeoutException) {
                view.showSocketException();
            } else if (e instanceof IOException) {
                view.showIOException();
            } else onFailure(e.getMessage());
        }
    }

    @Override
    public void onComplete() {
        BaseView view = weakReference.get();
        if (view != null) {
            onCompleted();
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