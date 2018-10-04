package com.shrikantbadwaik.tmdb.view.base;

public interface BaseView {
    boolean isDeviceOnline();

    void showDeviceOfflineError();

    void showLoading();

    void hideLoading();

    void showSuccessMessage(String success);

    void showErrorMessage(String error);

    void showSocketException();

    void showIOException();
}