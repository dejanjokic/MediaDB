package net.dejanjokic.mediadb.ui.movie.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.fragment_movie_details.*
import net.dejanjokic.mediadb.App
import net.dejanjokic.mediadb.R
import net.dejanjokic.mediadb.data.model.MovieDetails
import net.dejanjokic.mediadb.data.model.fullBackdropPath
import net.dejanjokic.mediadb.data.model.fullPosterPath
import net.dejanjokic.mediadb.di.component.DaggerMoviesComponent
import net.dejanjokic.mediadb.di.module.ApplicationModule
import net.dejanjokic.mediadb.util.Constants.UI.KEY_MOVIE_ID
import net.dejanjokic.mediadb.util.ext.gone
import net.dejanjokic.mediadb.util.ext.visible
import net.dejanjokic.mediadb.util.glide.GlideApp
import timber.log.Timber
import javax.inject.Inject

class MovieDetailsFragment : Fragment(), MovieDetailsContract.View {

    @Inject lateinit var presenter: MovieDetailsContract.Presenter

    companion object {
        fun newInstance(movieId: Long) = MovieDetailsFragment().apply {
            arguments = Bundle().apply {
                putLong(KEY_MOVIE_ID, movieId)
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
        inflater.inflate(R.layout.fragment_movie_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.apply {
            attach(this@MovieDetailsFragment)
            getMovieDetails(arguments!!.getLong(KEY_MOVIE_ID))
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
        loadingAnimMovieDetails?.visible()
    }

    override fun hideLoading() {
        loadingAnimMovieDetails?.gone()
    }

    override fun showErrorMessage(errorMessage: String?) {
        MaterialDialog(context!!).show {
            message(text = errorMessage)
            positiveButton(R.string.retry) {
                presenter.getMovieDetails(arguments!!.getLong(KEY_MOVIE_ID))
            }
        }
        Timber.e(errorMessage)
    }

    override fun showMovieDetails(movie: MovieDetails) = with(movie) {
        GlideApp.with(this@MovieDetailsFragment)
            .load(fullBackdropPath())
            .into(imageViewMovieDetailsBackdrop)

        GlideApp.with(this@MovieDetailsFragment)
            .load(fullPosterPath())
            .into(imageViewMovieDetailsPoster)

        textViewMovieDetailsTitle.text = title
        textViewMovieDetailsTagline.text = tagline
        textViewMovieDetailsOverview.text = overview
        textViewMovieDetailsRating.text = voteAverage.toString()
        textViewMovieDetailsRuntime.text = "$runtime min"

    }
}