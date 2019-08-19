package net.dejanjokic.mediadb.ui.movie.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.fragment_movie_list.*
import net.dejanjokic.mediadb.App
import net.dejanjokic.mediadb.R
import net.dejanjokic.mediadb.data.model.Movie
import net.dejanjokic.mediadb.di.component.DaggerMoviesComponent
import net.dejanjokic.mediadb.di.module.ApplicationModule
import net.dejanjokic.mediadb.ui.movie.details.MovieDetailsFragment
import net.dejanjokic.mediadb.util.Constants.UI.KEY_MOVIE_FRAGMENT
import net.dejanjokic.mediadb.util.Constants.UI.KEY_MOVIE_QUERY
import net.dejanjokic.mediadb.util.ext.gone
import net.dejanjokic.mediadb.util.ext.visible
import timber.log.Timber
import javax.inject.Inject

class MovieListFragment : Fragment(), MovieListContract.View {

    @Inject lateinit var presenter: MovieListContract.Presenter

    private val movieAdapter = MovieAdapter{
        showMovieDetails(it.id)
    }

    companion object {
        fun newInstance(query: String?) = MovieListFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_MOVIE_QUERY, query)
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        DaggerMoviesComponent.builder()
            .applicationModule(ApplicationModule(activity!!.application as App))
            .build()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_movie_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.apply {
            attach(this@MovieListFragment)
            getMovies(arguments?.getString(KEY_MOVIE_QUERY))
        }

        recyclerViewMovies.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
    }

    override fun onStart() {
        super.onStart()
        if (!presenter.isAttached) presenter.attach(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        presenter.detach()
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        if (presenter.isAttached) presenter.detach()
        super.onDestroyView()
    }

    override fun showLoading() {
        loadingAnimMovieList?.visible()
        recyclerViewMovies?.gone()
    }

    override fun hideLoading() {
        loadingAnimMovieList?.gone()
        recyclerViewMovies?.visible()
    }

    override fun showErrorMessage(errorMessage: String?) {
        MaterialDialog(context!!).show {
            message(text = errorMessage)
            positiveButton(R.string.retry) {
                presenter.getMovies(arguments?.getString(KEY_MOVIE_QUERY))
            }
        }
        Timber.e(errorMessage)
    }

    override fun showMovies(movies: List<Movie>) {
        movieAdapter.submitList(movies)
    }

    override fun showMovieDetails(movieId: Long) {
        activity?.supportFragmentManager!!.beginTransaction()
            .replace(R.id.containerMain, MovieDetailsFragment.newInstance(movieId))
            .addToBackStack(KEY_MOVIE_FRAGMENT)
            .commit()
    }
}