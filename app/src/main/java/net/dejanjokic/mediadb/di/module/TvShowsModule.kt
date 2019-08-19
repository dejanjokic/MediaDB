package net.dejanjokic.mediadb.di.module

import dagger.Module
import dagger.Provides
import net.dejanjokic.mediadb.data.Repository
import net.dejanjokic.mediadb.di.scope.ApplicationScope
import net.dejanjokic.mediadb.ui.tv.details.TvShowDetailsContract
import net.dejanjokic.mediadb.ui.tv.details.TvShowDetailsPresenter
import net.dejanjokic.mediadb.ui.tv.list.TvShowListContract
import net.dejanjokic.mediadb.ui.tv.list.TvShowListPresenter

@Module(includes = [DataModule::class])
class TvShowsModule {

    @ApplicationScope
    @Provides
    fun provideTvShowListPresenter(repository: Repository): TvShowListContract.Presenter =
        TvShowListPresenter(repository)

    @ApplicationScope
    @Provides
    fun provideTvShowDetailsPresenter(repository: Repository): TvShowDetailsContract.Presenter =
        TvShowDetailsPresenter(repository)
}