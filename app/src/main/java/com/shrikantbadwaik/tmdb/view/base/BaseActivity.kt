package com.shrikantbadwaik.tmdb.view.base

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


abstract class BaseActivity<VDB : ViewDataBinding, BVM : BaseViewModel<*>> : AppCompatActivity(), BaseView {
    private lateinit var viewDataBinding: VDB
    private var baseViewModel: BVM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependency()
        super.onCreate(savedInstanceState)
        initViewDataBinding()
        initView()
    }

    override fun onStart() {
        super.onStart()
        baseViewModel?.onActivityStarted()
    }

    override fun onResume() {
        super.onResume()
        baseViewModel?.onActivityResumed()
    }

    override fun onPause() {
        super.onPause()
        baseViewModel?.onActivityPaused()
    }

    override fun onStop() {
        super.onStop()
        baseViewModel?.onActivityStopped()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewDataBinding.unbind()
        baseViewModel?.onActivityDestroyed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun isDeviceOnline(): Boolean = true

    override fun showDeviceOfflineError() {
    }

    override fun showSuccessMessage(message: String) {
    }

    override fun showErrorMessage(message: String) {
    }

    override fun showSocketException() {
    }

    override fun showIOException() {
    }

    private fun initViewDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResource)
        baseViewModel = baseViewModel?.let { baseViewModel } ?: viewModel
        (viewDataBinding as? ViewDataBinding)?.setVariable(variableId, baseViewModel)
        (viewDataBinding as? ViewDataBinding)?.executePendingBindings()
    }

    fun viewDataBinding(): VDB {
        return viewDataBinding
    }

    protected abstract fun injectDependency()

    protected abstract val layoutResource: Int

    protected abstract val viewModel: BVM

    protected abstract val variableId: Int

    protected abstract fun initView()
}