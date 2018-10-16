package com.shrikantbadwaik.tmdb.domain.helper.rx

import io.reactivex.Scheduler

interface SchedulerProvider {
    val ui: Scheduler

    val computation: Scheduler

    val io: Scheduler
}