package com.shrikantbadwaik.tmdb.view.moviedetails;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;

import com.shrikantbadwaik.tmdb.BR;
import com.shrikantbadwaik.tmdb.R;
import com.shrikantbadwaik.tmdb.databinding.MovieDetailsActivityBinding;
import com.shrikantbadwaik.tmdb.view.base.BaseActivity;
import com.shrikantbadwaik.tmdb.viewmodel.MovieDetailsViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MovieDetailsActivity extends BaseActivity<MovieDetailsActivityBinding, MovieDetailsViewModel>
        implements MovieDetailsView {
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    MovieDetailsViewPagerAdapter adapter;

    private MovieDetailsViewModel viewModel;
    private MovieDetailsActivityBinding activityBinding;

    @Override
    protected void injectDependency() {
        AndroidInjection.inject(this);
    }

    @Override
    protected int layoutResource() {
        return R.layout.movie_details_activity;
    }

    @Override
    protected MovieDetailsViewModel viewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailsViewModel.class);
        return viewModel;
    }

    @Override
    protected int bindingVariable() {
        return BR.viewModel;
    }

    @Override
    protected void initView() {
        activityBinding = viewDataBinding();
        viewModel.attachView(this);

        setupToolbar();
        activityBinding.movieDetailsActivityViewPager.setAdapter(adapter);
        viewModel.intentExtras(getIntent().getExtras());
    }

    private void setupToolbar() {
        setSupportActionBar(activityBinding.movieDetailsActivityToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}