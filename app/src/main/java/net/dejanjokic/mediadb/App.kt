package net.dejanjokic.mediadb

import android.app.Application
import net.dejanjokic.mediadb.di.component.ApplicationComponent
import net.dejanjokic.mediadb.di.component.DaggerApplicationComponent
import net.dejanjokic.mediadb.util.log.CustomTimberTree
import timber.log.Timber

// TODO: Fix the scoping mess
class App : Application() {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(CustomTimberTree())

        component = DaggerApplicationComponent.builder().build()
    }

    fun component() = component
}