package net.dejanjokic.mediadb.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import net.dejanjokic.mediadb.App
import net.dejanjokic.mediadb.di.scope.ApplicationScope

@Module
class ApplicationModule(private val app: App) {

    @ApplicationScope
    @Provides
    fun provideApp(): Application = app

    @ApplicationScope
    @Provides
    fun provideContext(): Context = app.applicationContext
}