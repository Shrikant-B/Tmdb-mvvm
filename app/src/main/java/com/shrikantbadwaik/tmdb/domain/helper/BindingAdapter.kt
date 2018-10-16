package com.shrikantbadwaik.tmdb.domain.helper

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.shrikantbadwaik.tmdb.data.model.Movie
import com.shrikantbadwaik.tmdb.view.movies.MoviesRecyclerAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("android:adapter")
    fun showMovieList(recyclerView: RecyclerView, movieList: MutableList<Movie>) {
        val adapter = recyclerView.adapter as MoviesRecyclerAdapter?
        adapter?.let { moviesAdapter ->
            moviesAdapter.setMovieList(movieList)
            moviesAdapter.notifyDataSetChanged()
        }
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(imageView: ImageView, url: String) {
        Glide.with(imageView.context).load(url).into(imageView)
    }
}