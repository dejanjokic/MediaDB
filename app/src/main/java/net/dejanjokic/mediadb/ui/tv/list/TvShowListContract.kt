package net.dejanjokic.mediadb.ui.tv.list

import net.dejanjokic.mediadb.data.model.TvShow
import net.dejanjokic.mediadb.ui.base.BaseContract

interface TvShowListContract {

    interface View : BaseContract.View {

        fun showTvShows(tvShows: List<TvShow>)

        fun showTvShowDetails(tvShowId: Long)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun getTvShows(query: String?)
    }
}