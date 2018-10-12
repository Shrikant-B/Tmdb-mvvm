package com.shrikantbadwaik.tmdb.data.di.component

import android.app.Application
import com.shrikantbadwaik.tmdb.UpcomingMoviesApplication
import com.shrikantbadwaik.tmdb.data.di.module.ActivityBuilder
import com.shrikantbadwaik.tmdb.data.di.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, AndroidInjectionModule::class,
        ApplicationModule::class, ActivityBuilder::class]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): ApplicationComponent.Builder

        fun build(): ApplicationComponent
    }

    fun inject(upcomingMoviesApplication: UpcomingMoviesApplication)
}