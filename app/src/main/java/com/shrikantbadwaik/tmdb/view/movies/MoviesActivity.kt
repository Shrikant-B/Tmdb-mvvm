package com.shrikantbadwaik.tmdb.view.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.shrikantbadwaik.tmdb.BR
import com.shrikantbadwaik.tmdb.R
import com.shrikantbadwaik.tmdb.databinding.ActivityMoviesBinding
import com.shrikantbadwaik.tmdb.domain.helper.ViewModelProviderFactory
import com.shrikantbadwaik.tmdb.view.base.BaseActivity
import com.shrikantbadwaik.tmdb.viewmodel.MoviesActivityViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MoviesActivity : BaseActivity<ActivityMoviesBinding, MoviesActivityViewModel>(), MoviesView {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    @Inject
    lateinit var adapter: MoviesRecyclerAdapter

    private lateinit var activityBinding: ActivityMoviesBinding
    private lateinit var moviesActivityViewModel: MoviesActivityViewModel

    override fun injectDependency() {
        AndroidInjection.inject(this)
    }

    override val layoutResource: Int = R.layout.activity_movies

    override val viewModel: MoviesActivityViewModel
        get() {
            moviesActivityViewModel = ViewModelProviders.of(this, factory).get(MoviesActivityViewModel::class.java)
            return moviesActivityViewModel
        }

    override val variableId: Int = BR.viewModel

    override fun initView() {
        activityBinding = viewDataBinding()
        moviesActivityViewModel.attachView(this)
        setupRecyclerView()
        moviesActivityViewModel.getUpcomingMovies()
        moviesActivityViewModel.getMovieListLiveData()
            .observe(this, Observer { moviesActivityViewModel.addMovieListToObservable(it) })
    }

    private fun setupRecyclerView() {
        activityBinding.activityMoviesRecyclerView.layoutManager =
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                } else GridLayoutManager(this, 3)
        activityBinding.activityMoviesRecyclerView.adapter = adapter
        /*adapter.setAdapterCallback(object : MoviesRecyclerAdapter.AdapterCallback {
            override fun showMovieDetails(movie: Movie) {

            }
        })*/
    }
}