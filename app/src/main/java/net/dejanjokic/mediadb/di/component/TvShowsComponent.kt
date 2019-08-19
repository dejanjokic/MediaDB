package net.dejanjokic.mediadb.di.component

import dagger.Component
import net.dejanjokic.mediadb.di.module.TvShowsModule
import net.dejanjokic.mediadb.di.scope.ApplicationScope
import net.dejanjokic.mediadb.ui.tv.details.TvShowDetailsFragment
import net.dejanjokic.mediadb.ui.tv.list.TvShowListFragment

// TODO: Fix the scoping mess
@ApplicationScope
@Component(modules = [TvShowsModule::class])
interface TvShowsComponent {

    fun inject(fragment: TvShowListFragment)

    fun inject(fragment: TvShowDetailsFragment)
}