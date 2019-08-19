package net.dejanjokic.mediadb.ui.movie.list

import net.dejanjokic.mediadb.data.model.Movie
import net.dejanjokic.mediadb.ui.base.BaseContract

interface MovieListContract {

    interface View : BaseContract.View {

        fun showMovies(movies: List<Movie>)

        fun showMovieDetails(movieId: Long)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun getMovies(query: String?)
    }
}