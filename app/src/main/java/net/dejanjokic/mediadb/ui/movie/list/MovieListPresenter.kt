package net.dejanjokic.mediadb.ui.movie.list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import net.dejanjokic.mediadb.data.Repository
import net.dejanjokic.mediadb.ui.base.BasePresenter
import javax.inject.Inject

class MovieListPresenter @Inject constructor(
    private val repository: Repository
) : BasePresenter<MovieListContract.View>(), MovieListContract.Presenter {

    override fun getMovies(query: String?) {

        view?.showLoading()

        repository.getMovies(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                view?.apply {
                    showMovies(response.movies)
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