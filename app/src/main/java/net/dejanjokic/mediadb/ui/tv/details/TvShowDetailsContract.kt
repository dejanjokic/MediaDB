package net.dejanjokic.mediadb.ui.tv.details

import net.dejanjokic.mediadb.data.model.TvShowDetails
import net.dejanjokic.mediadb.ui.base.BaseContract

interface TvShowDetailsContract {

    interface View : BaseContract.View {

        fun showTvShowDetails(tvShow: TvShowDetails)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun getTvShowDetails(id: Long)
    }
}