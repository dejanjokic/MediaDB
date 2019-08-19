package net.dejanjokic.mediadb.ui.movie.details

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import net.dejanjokic.mediadb.data.Repository
import net.dejanjokic.mediadb.ui.base.BasePresenter
import timber.log.Timber
import javax.inject.Inject

class MovieDetailsPresenter @Inject constructor(
    private val repository: Repository
) : BasePresenter<MovieDetailsContract.View>(), MovieDetailsContract.Presenter {

    override fun getMovieDetails(id: Long) {
        view?.showLoading()
        repository.getCachedMovieDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movieDetails ->
                if (movieDetails.isEmpty())
                    cacheApiData(id)
                else
                    view?.apply {
                        hideLoading()
                        showMovieDetails(movieDetails.first())
                }
            }, {
                view?.apply {
                    hideLoading()
                    showErrorMessage(it.localizedMessage)
                }
            }).addTo(compositeDisposable)
    }

    private fun cacheApiData(id: Long) {
        repository.getNewMovieDetails(id)
            .subscribeOn(Schedulers.io())
            .doOnSuccess { repository.cacheMovieDetails(it).subscribe() }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { Timber.e("NetworkError: ${it.localizedMessage}") }
            .subscribe()
            .addTo(compositeDisposable)
    }
}