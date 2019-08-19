package net.dejanjokic.mediadb.ui.main

import net.dejanjokic.mediadb.ui.DataType
import net.dejanjokic.mediadb.ui.base.BaseContract

interface MainContract {

    interface View : BaseContract.View {

        fun getData(type: DataType? = null, query: String? = "")

        fun showMovies(query: String?)

        fun showTvShows(query: String?)
    }
}