package com.shrikantbadwaik.tmdb.view.base;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<VDB extends ViewDataBinding, BVM extends BaseViewModel> extends AppCompatActivity implements BaseView {
    private VDB viewDataBinding;
    private BVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        injectDependency();
        super.onCreate(savedInstanceState);
        initViewDataBinding();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (viewModel != null) {
            viewModel.onActivityStarted();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (viewModel != null) {
            viewModel.onActivityResumed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (viewModel != null) {
            viewModel.onActivityPaused();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (viewModel != null) {
            viewModel.onActivityStopped();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewDataBinding.unbind();
        if (viewModel != null) {
            viewModel.onActivityDestroyed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModel.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        viewModel.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean isDeviceOnline() {
        return true;
    }

    @Override
    public void showDeviceOfflineError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showSuccessMessage(String success) {

    }

    @Override
    public void showErrorMessage(String error) {

    }

    @Override
    public void showSocketException() {

    }

    @Override
    public void showIOException() {

    }

    public VDB viewDataBinding() {
        return viewDataBinding;
    }

    private void initViewDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResource());
        viewModel = viewModel == null ? viewModel() : viewModel;
        viewDataBinding.setVariable(bindingVariable(), viewModel);
        viewDataBinding.executePendingBindings();
    }

    protected abstract void injectDependency();

    protected abstract int layoutResource();

    protected abstract BVM viewModel();

    protected abstract int bindingVariable();

    protected abstract void initView();
}