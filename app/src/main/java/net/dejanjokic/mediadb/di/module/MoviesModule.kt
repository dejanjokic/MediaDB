package net.dejanjokic.mediadb.di.module

import dagger.Module
import dagger.Provides
import net.dejanjokic.mediadb.data.Repository
import net.dejanjokic.mediadb.di.scope.ApplicationScope
import net.dejanjokic.mediadb.ui.movie.details.MovieDetailsContract
import net.dejanjokic.mediadb.ui.movie.details.MovieDetailsPresenter
import net.dejanjokic.mediadb.ui.movie.list.MovieListContract
import net.dejanjokic.mediadb.ui.movie.list.MovieListPresenter

@Module(includes = [DataModule::class])
class MoviesModule {

    @ApplicationScope
    @Provides
    fun provideMovieListPresenter(repository: Repository): MovieListContract.Presenter =
        MovieListPresenter(repository)

    @ApplicationScope
    @Provides
    fun provideMovieDetailsPresenter(repository: Repository): MovieDetailsContract.Presenter =
        MovieDetailsPresenter(repository)
}