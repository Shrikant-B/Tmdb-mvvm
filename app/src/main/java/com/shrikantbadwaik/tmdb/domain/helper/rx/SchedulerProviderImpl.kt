package com.shrikantbadwaik.tmdb.domain.helper.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProviderImpl : SchedulerProvider {
    override val ui: Scheduler
        get() {
            return AndroidSchedulers.mainThread()
        }

    override val computation: Scheduler
        get() {
            return Schedulers.computation()
        }

    override val io: Scheduler
        get() {
            return Schedulers.io()
        }
}