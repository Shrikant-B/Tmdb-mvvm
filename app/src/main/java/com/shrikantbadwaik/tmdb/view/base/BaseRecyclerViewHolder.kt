package com.shrikantbadwaik.tmdb.view.base

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseRecyclerViewHolder<DATA>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(bind: DATA, position: Int)
}