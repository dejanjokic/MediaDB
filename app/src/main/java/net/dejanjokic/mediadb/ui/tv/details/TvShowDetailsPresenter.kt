package net.dejanjokic.mediadb.ui.tv.details

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import net.dejanjokic.mediadb.data.Repository
import net.dejanjokic.mediadb.ui.base.BasePresenter
import timber.log.Timber
import javax.inject.Inject

class TvShowDetailsPresenter @Inject constructor(
    private val repository: Repository
) : BasePresenter<TvShowDetailsContract.View>(), TvShowDetailsContract.Presenter {

    override fun getTvShowDetails(id: Long) {
        view?.showLoading()
        repository.getCachedTvShowDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ tvShowDetails ->
                if (tvShowDetails.isEmpty())
                    cacheApiData(id)
                else
                    view?.apply {
                        hideLoading()
                        showTvShowDetails(tvShowDetails.first())
                    }
            }, { t ->
                view?.apply {
                    hideLoading()
                    showErrorMessage(t.localizedMessage)
                }
            }).addTo(compositeDisposable)
    }

    private fun cacheApiData(id: Long) {
        repository.getNewTvShowDetails(id)
            .subscribeOn(Schedulers.io())
            .doOnSuccess { repository.cacheTvShowDetails(it).subscribe() }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { Timber.e("NetworkError: ${it.localizedMessage}") }
            .subscribe()
            .addTo(compositeDisposable)
    }
}