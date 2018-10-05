package com.shrikantbadwaik.tmdb.view.upcomingmovies;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.shrikantbadwaik.tmdb.BR;
import com.shrikantbadwaik.tmdb.R;
import com.shrikantbadwaik.tmdb.data.model.Movie;
import com.shrikantbadwaik.tmdb.databinding.UpcomingMoviesActivityBinding;
import com.shrikantbadwaik.tmdb.view.base.BaseActivity;
import com.shrikantbadwaik.tmdb.viewmodel.UpcomingMoviesViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class UpcomingMoviesActivity extends BaseActivity<UpcomingMoviesActivityBinding, UpcomingMoviesViewModel>
        implements UpcomingMoviesView, HasSupportFragmentInjector {
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    DispatchingAndroidInjector<Fragment> androidInjector;
    @Inject
    UpcomingMoviesAdapter adapter;

    private UpcomingMoviesViewModel viewModel;
    private UpcomingMoviesActivityBinding activityBinding;

    @Override
    protected void injectDependency() {
        AndroidInjection.inject(this);
    }

    @Override
    protected int layoutResource() {
        return R.layout.upcoming_movies_activity;
    }

    @Override
    protected UpcomingMoviesViewModel viewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UpcomingMoviesViewModel.class);
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

        setupRecyclerView();
        viewModel.getUpcomingMovies();
    }

    private void setupRecyclerView() {
        activityBinding.upcomingMoviesActivityRecyclerView.setAdapter(adapter);
        activityBinding.upcomingMoviesActivityRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        activityBinding.upcomingMoviesActivityRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return androidInjector;
    }

    @Override
    public void showUpcomingMovies(List<Movie> movieList) {
        adapter.setMovieList(movieList);
        adapter.notifyDataSetChanged();
    }
}