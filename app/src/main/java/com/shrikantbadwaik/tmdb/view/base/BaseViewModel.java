package com.shrikantbadwaik.tmdb.view.base;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;

import com.shrikantbadwaik.tmdb.data.repository.Repository;

import java.lang.ref.WeakReference;

public abstract class BaseViewModel<V extends BaseView> extends ViewModel {
    private final Repository repository;
    private WeakReference<V> view;

    public BaseViewModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    protected void onCleared() {
        view.clear();
    }

    public void attachView(V view) {
        this.view = new WeakReference<>(view);
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public V getView() {
        return view.get();
    }

    public Repository repository() {
        return repository;
    }

    public void onActivityStarted() {
    }

    public void onActivityResumed() {
    }

    public void onActivityPaused() {
    }

    public void onActivityStopped() {
    }

    public void onActivityDestroyed() {
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    }
}