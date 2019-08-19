package net.dejanjokic.mediadb.ui.base

interface BaseContract {

    interface View {

        fun showLoading() {}

        fun hideLoading() {}

        fun showErrorMessage(errorMessage: String?)
    }

    interface Presenter<V : View> {

        var isAttached: Boolean

        fun attach(view: V)

        fun detach()
    }
}