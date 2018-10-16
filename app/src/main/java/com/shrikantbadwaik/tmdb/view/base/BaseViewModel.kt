package com.shrikantbadwaik.tmdb.view.base

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.shrikantbadwaik.tmdb.data.repository.Repository
import java.lang.ref.WeakReference


abstract class BaseViewModel<View : BaseView>(private val repository: Repository) : ViewModel() {
    private lateinit var view: WeakReference<View>
    private val loading: ObservableBoolean = ObservableBoolean()

    fun attachView(view: View) {
        this.view = WeakReference(view)
    }

    fun getView(): View? {
        return view.get()
    }

    fun repository(): Repository {
        return repository
    }

    fun setLoading(loading: Boolean) {
        this.loading.set(loading)
    }

    fun isLoading() = loading

    open fun onActivityStarted() {}

    open fun onActivityResumed() {}

    open fun onActivityPaused() {}

    open fun onActivityStopped() {}

    open fun onActivityDestroyed() {}

    override fun onCleared() {
        super.onCleared()
        view.clear()
    }
}