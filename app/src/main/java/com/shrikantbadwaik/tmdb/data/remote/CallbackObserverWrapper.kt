package com.shrikantbadwaik.tmdb.data.remote

import com.shrikantbadwaik.tmdb.domain.Constants
import com.shrikantbadwaik.tmdb.view.base.BaseView
import io.reactivex.observers.DisposableObserver
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.lang.ref.WeakReference
import java.net.SocketTimeoutException

abstract class CallbackObserverWrapper<RESULT> constructor(
    view: BaseView?
) : DisposableObserver<RESULT>() {
    //MvpView is just a reference of a View in MVP
    private val weakReference: WeakReference<BaseView?> = WeakReference(view)

    protected abstract fun onSuccess(result: RESULT)

    protected abstract fun onFailure(error: String)

    protected open fun onCompleted() {

    }

    override fun onNext(result: RESULT) {
        val view = weakReference.get()
        if (view != null) {
            onSuccess(result)
        }
    }

    override fun onError(e: Throwable) {
        val view = weakReference.get()
        if (view != null) {
            if (e is HttpException) {
                val errorCode = e.response().code()
                if (errorCode == 503 || errorCode == 500) {
                    val errorMessage = e.getLocalizedMessage()
                    onFailure(errorMessage)
                } else {
                    val responseBody = e.response().errorBody()
                    onFailure(getErrorMessage(responseBody ?: return))
                }
            } else if (e is SocketTimeoutException) {
                view.showSocketException()
            } else if (e is IOException) {
                view.showIOException()
            } else onFailure(e.message.toString())
        }
    }

    override fun onComplete() {
        val view = weakReference.get()
        if (view != null) {
            onCompleted()
        }
    }

    private fun getErrorMessage(responseBody: ResponseBody): String {
        return try {
            val jsonObject = JSONObject(responseBody.string())
            jsonObject.getString(Constants.API_ERROR_RESPONSE_KEY)
        } catch (e: Exception) {
            e.message.toString()
        }
    }
}