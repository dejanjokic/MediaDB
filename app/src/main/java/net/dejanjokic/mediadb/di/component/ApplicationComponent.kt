package net.dejanjokic.mediadb.di.component

import dagger.Component
import net.dejanjokic.mediadb.di.module.ApplicationModule
import net.dejanjokic.mediadb.di.module.DataModule
import net.dejanjokic.mediadb.di.scope.ApplicationScope
import net.dejanjokic.mediadb.ui.main.MainActivity

@ApplicationScope
@Component(modules = [ApplicationModule::class, DataModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
}