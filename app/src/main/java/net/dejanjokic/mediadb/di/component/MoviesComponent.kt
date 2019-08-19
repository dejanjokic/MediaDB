package net.dejanjokic.mediadb.di.component

import dagger.Component
import net.dejanjokic.mediadb.di.module.MoviesModule
import net.dejanjokic.mediadb.di.scope.ApplicationScope
import net.dejanjokic.mediadb.ui.movie.details.MovieDetailsFragment
import net.dejanjokic.mediadb.ui.movie.list.MovieListFragment

@ApplicationScope
@Component(modules = [MoviesModule::class])
interface MoviesComponent {

    fun inject(fragment: MovieListFragment)

    fun inject(fragment: MovieDetailsFragment)
}