package com.shrikantbadwaik.tmdb.view.moviedetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.shrikantbadwaik.tmdb.BR
import com.shrikantbadwaik.tmdb.R
import com.shrikantbadwaik.tmdb.databinding.ActivityMovieDetailsBinding
import com.shrikantbadwaik.tmdb.view.base.BaseActivity
import com.shrikantbadwaik.tmdb.viewmodel.MovieDetailsActivityViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MovieDetailsActivity : BaseActivity<ActivityMovieDetailsBinding, MovieDetailsActivityViewModel>(),
    MovieDetailsView {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    @Inject
    lateinit var adapter: MovieDetailsPagerAdapter

    private lateinit var activityBinding: ActivityMovieDetailsBinding
    private lateinit var movieDetailsActivityViewModel: MovieDetailsActivityViewModel

    override fun injectDependency() {
        AndroidInjection.inject(this)
    }

    override val layoutResource: Int = R.layout.activity_movie_details

    override val viewModel: MovieDetailsActivityViewModel
        get() {
            movieDetailsActivityViewModel =
                    ViewModelProviders.of(this, factory).get(MovieDetailsActivityViewModel::class.java)
            return movieDetailsActivityViewModel
        }

    override val variableId: Int = BR.viewModel

    override fun initView() {
        activityBinding = viewDataBinding()
        movieDetailsActivityViewModel.attachView(this)
        setupToolbar()
        activityBinding.activityMovieDetailsViewPager.adapter = adapter
        movieDetailsActivityViewModel.intentExtras(intent.extras)
        movieDetailsActivityViewModel.getImageListLiveData()
            .observe(this, Observer { movieDetailsActivityViewModel.addImagesToObservable(it) })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupToolbar() {
        setSupportActionBar(activityBinding.activityMovieDetailsToolbar)
        supportActionBar?.let { actionBar ->
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowHomeEnabled(true)
            actionBar.setDisplayShowTitleEnabled(false)
        }
    }
}