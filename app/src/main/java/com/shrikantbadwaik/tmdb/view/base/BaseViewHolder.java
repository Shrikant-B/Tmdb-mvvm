package com.shrikantbadwaik.tmdb.view.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<DATA> extends RecyclerView.ViewHolder {
    public BaseViewHolder(@NonNull View view) {
        super(view);
    }

    public abstract void onBind(DATA data, int position);
}