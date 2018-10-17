package com.shrikantbadwaik.tmdb.view.moviedetails

import android.databinding.DataBindingUtil
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shrikantbadwaik.tmdb.BR
import com.shrikantbadwaik.tmdb.R
import com.shrikantbadwaik.tmdb.data.model.Image
import com.shrikantbadwaik.tmdb.databinding.LayoutMovieDetailsAdapterViewHolderBinding
import com.shrikantbadwaik.tmdb.viewmodel.MovieDetailsPagerAdapterViewModel
import javax.inject.Inject

class MovieDetailsPagerAdapter @Inject constructor() : PagerAdapter() {
    private val imageList: MutableList<Image> = ArrayList()

    fun setImageList(imageList: MutableList<Image>?) {
        imageList?.let { it ->
            if (!it.isEmpty()) {
                this.imageList.clear()
                this.imageList.addAll(it)
            }
        }
    }

    override fun isViewFromObject(view: View, any: Any): Boolean {
        return view == any
    }

    override fun getCount(): Int {
        if (imageList.isEmpty()) return 0
        return if (imageList.size >= 5) 5 else imageList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val adapterBinding: LayoutMovieDetailsAdapterViewHolderBinding = DataBindingUtil.inflate(
            LayoutInflater.from(container.context),
            R.layout.layout_movie_details_adapter_view_holder, container, false
        )
        val viewHolder = ViewHolder(adapterBinding)
        viewHolder.onBind(imageList[position])
        container.addView(adapterBinding.root)
        return adapterBinding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
        container.removeView(any as View)
    }

    inner class ViewHolder(private val adapterBinding: LayoutMovieDetailsAdapterViewHolderBinding) {

        fun onBind(image: Image) {
            adapterBinding.setVariable(BR.viewModel, MovieDetailsPagerAdapterViewModel(image))
            adapterBinding.executePendingBindings()
        }
    }
}