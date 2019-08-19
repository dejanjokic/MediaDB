package net.dejanjokic.mediadb.ui.tv.list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import net.dejanjokic.mediadb.data.Repository
import net.dejanjokic.mediadb.ui.base.BasePresenter
import javax.inject.Inject

class TvShowListPresenter @Inject constructor(
    private val repository: Repository
) : BasePresenter<TvShowListContract.View>(), TvShowListContract.Presenter {

    override fun getTvShows(query: String?) {

        view?.showLoading()

        repository.getTvShows(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                view?.apply {
                    showTvShows(response.tvShows)
                    hideLoading()
                }
            }, { t ->
                view?.apply {
                    showErrorMessage(t.localizedMessage)
                    hideLoading()
                }
            }).addTo(compositeDisposable)
    }
}