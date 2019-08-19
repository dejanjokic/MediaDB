package net.dejanjokic.mediadb.ui.base

import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    var view: V? = null

    var compositeDisposable = CompositeDisposable()

    override fun attach(view: V) {
        this.view = view
    }

    override fun detach() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
        view = null
    }

    override var isAttached = view != null
}