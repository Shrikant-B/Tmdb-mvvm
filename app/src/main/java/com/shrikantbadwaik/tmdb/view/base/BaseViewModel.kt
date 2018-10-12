package com.shrikantbadwaik.tmdb.view.base

import android.arch.lifecycle.ViewModel
import com.shrikantbadwaik.tmdb.data.repository.Repository
import java.lang.ref.WeakReference


abstract class BaseViewModel<View : BaseView>(private val repository: Repository) : ViewModel() {
    private var view: WeakReference<View>? = null

    val isViewAttached: Boolean get() = view != null

    fun attachView(view: View) {
        this.view = WeakReference(view)
    }

    fun getView(): View? {
        return view?.get()
    }

    fun repository(): Repository {
        return repository
    }

    abstract fun onActivityStarted()

    fun onActivityResumed() {}

    fun onActivityPaused() {}

    fun onActivityStopped() {}

    fun onActivityDestroyed() {}

    override fun onCleared() {
        super.onCleared()
        view?.clear()
    }
}