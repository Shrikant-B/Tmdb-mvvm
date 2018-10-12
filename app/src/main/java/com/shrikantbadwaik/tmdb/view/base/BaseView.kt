package com.shrikantbadwaik.tmdb.view.base

interface BaseView {
    fun isDeviceOnline(): Boolean
    fun showDeviceOfflineError()
    fun showSuccessMessage(message: String)
    fun showErrorMessage(message: String)
    fun showSocketException()
    fun showIOException()
}