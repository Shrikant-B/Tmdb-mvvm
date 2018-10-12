package com.shrikantbadwaik.tmdb

import android.app.Activity
import android.app.Application
import com.crashlytics.android.Crashlytics
import com.shrikantbadwaik.tmdb.data.di.ApplicationInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.fabric.sdk.android.Fabric
import javax.inject.Inject


class UpcomingMoviesApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        val fabric = Fabric.Builder(this)
            .kits(Crashlytics())
            .debuggable(true)
            .build()
        Fabric.with(fabric)
        ApplicationInjector.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = androidInjector
}