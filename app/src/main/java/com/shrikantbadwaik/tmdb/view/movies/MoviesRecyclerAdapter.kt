package com.shrikantbadwaik.tmdb.view.movies

import android.content.res.Configuration
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.shrikantbadwaik.tmdb.BR
import com.shrikantbadwaik.tmdb.R
import com.shrikantbadwaik.tmdb.data.model.Movie
import com.shrikantbadwaik.tmdb.databinding.LayoutMoviesAdapterViewHolderBinding
import com.shrikantbadwaik.tmdb.view.base.BaseRecyclerViewHolder
import com.shrikantbadwaik.tmdb.viewmodel.MoviesRecyclerAdapterViewModel
import javax.inject.Inject

class MoviesRecyclerAdapter @Inject constructor(
    private val orientation: Int,
    private var movieList: MutableList<Movie> = ArrayList()
) : RecyclerView.Adapter<BaseRecyclerViewHolder<Movie>>() {
    private var callback: AdapterCallback? = null

    fun setMovieList(movieList: MutableList<Movie>) {
        if (!movieList.isEmpty()) {
            this.movieList.clear()
            this.movieList.addAll(movieList)
        }
    }

    fun clearList() {
        movieList.clear()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseRecyclerViewHolder<Movie> {
        return if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            val adapterBinding = DataBindingUtil.inflate<LayoutMoviesAdapterViewHolderBinding>(
                LayoutInflater.from(viewGroup.context), R.layout.layout_movies_adapter_view_holder, viewGroup, false
            )
            PortraitViewHolder(adapterBinding)
        } else {
            val adapterBinding = DataBindingUtil.inflate<LayoutMoviesAdapterViewHolderBinding>(
                LayoutInflater.from(viewGroup.context), R.layout.layout_movies_adapter_view_holder, viewGroup, false
            )
            LandscapeViewHolder(adapterBinding)
        }
    }

    override fun getItemCount(): Int {
        return if (!movieList.isEmpty()) movieList.size
        else 0
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<Movie>, position: Int) {
        val movie = movieList[position]
        holder.onBind(movie, position)
    }

    fun setAdapterCallback(callback: AdapterCallback) {
        this.callback = callback
    }

    interface AdapterCallback {
        fun showMovieDetails(movie: Movie)
    }

    open inner class ViewHolder(
        private val adapterBinding: LayoutMoviesAdapterViewHolderBinding
    ) : BaseRecyclerViewHolder<Movie>(adapterBinding.root), MoviesRecyclerAdapterViewModel.Callback {

        override fun onBind(bind: Movie, position: Int) {
            val viewModel = MoviesRecyclerAdapterViewModel(bind, this)
            adapterBinding.setVariable(BR.viewModel, viewModel)
            adapterBinding.executePendingBindings()
        }

        override fun onItemClicked(movie: Movie) {
            callback?.showMovieDetails(movie)
        }
    }

    inner class PortraitViewHolder(adapterBinding: LayoutMoviesAdapterViewHolderBinding) : ViewHolder(adapterBinding)

    inner class LandscapeViewHolder(adapterBinding: LayoutMoviesAdapterViewHolderBinding) : ViewHolder(adapterBinding)
}