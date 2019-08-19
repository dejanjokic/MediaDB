package net.dejanjokic.mediadb.ui.movie.details

import net.dejanjokic.mediadb.data.model.MovieDetails
import net.dejanjokic.mediadb.ui.base.BaseContract

interface MovieDetailsContract {

    interface View : BaseContract.View {

        fun showMovieDetails(movie: MovieDetails)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun getMovieDetails(id: Long)
    }
}