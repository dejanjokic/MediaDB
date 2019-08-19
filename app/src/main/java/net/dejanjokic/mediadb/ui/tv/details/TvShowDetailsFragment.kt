package net.dejanjokic.mediadb.ui.tv.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.fragment_tv_show_details.*
import net.dejanjokic.mediadb.App
import net.dejanjokic.mediadb.R
import net.dejanjokic.mediadb.data.model.TvShowDetails
import net.dejanjokic.mediadb.data.model.fullBackdropPath
import net.dejanjokic.mediadb.data.model.fullPosterPath
import net.dejanjokic.mediadb.di.component.DaggerTvShowsComponent
import net.dejanjokic.mediadb.di.module.ApplicationModule
import net.dejanjokic.mediadb.util.Constants.UI.KEY_TV_SHOW_ID
import net.dejanjokic.mediadb.util.ext.gone
import net.dejanjokic.mediadb.util.ext.visible
import net.dejanjokic.mediadb.util.glide.GlideApp
import timber.log.Timber
import javax.inject.Inject

class TvShowDetailsFragment : Fragment(), TvShowDetailsContract.View {

    @Inject lateinit var presenter: TvShowDetailsContract.Presenter

    companion object {
        fun newInstance(tvShowId: Long) = TvShowDetailsFragment().apply {
            arguments = Bundle().apply {
                putLong(KEY_TV_SHOW_ID, tvShowId)
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        DaggerTvShowsComponent.builder()
            .applicationModule(ApplicationModule(activity!!.application as App))
            .build()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_tv_show_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.apply {
            attach(this@TvShowDetailsFragment)
            getTvShowDetails(arguments!!.getLong(KEY_TV_SHOW_ID))
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
        loadingAnimTvShowDetails?.visible()
    }

    override fun hideLoading() {
        loadingAnimTvShowDetails?.gone()
    }

    override fun showErrorMessage(errorMessage: String?) {
        MaterialDialog(context!!).show {
            message(text = errorMessage)
            positiveButton(R.string.retry) {
                presenter.getTvShowDetails(arguments!!.getLong(KEY_TV_SHOW_ID))
            }
        }
        Timber.e(errorMessage)
    }

    override fun showTvShowDetails(tvShow: TvShowDetails) = with(tvShow) {
        GlideApp.with(this@TvShowDetailsFragment)
            .load(fullBackdropPath())
            .into(imageViewTvShowDetailsBackdrop)

        GlideApp.with(this@TvShowDetailsFragment)
            .load(fullPosterPath())
            .into(imageViewTvShowDetailsPoster)

        textViewTvShowDetailsTitle.text = title
        textViewTvShowDetailsOverview.text = overview
        textViewTvShowDetailsRating.text = voteAverage.toString()
    }
}