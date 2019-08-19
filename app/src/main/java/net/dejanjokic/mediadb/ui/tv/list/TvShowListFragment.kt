package net.dejanjokic.mediadb.ui.tv.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.fragment_tv_show_list.*
import net.dejanjokic.mediadb.App
import net.dejanjokic.mediadb.R
import net.dejanjokic.mediadb.data.model.TvShow
import net.dejanjokic.mediadb.di.component.DaggerTvShowsComponent
import net.dejanjokic.mediadb.di.module.ApplicationModule
import net.dejanjokic.mediadb.ui.tv.details.TvShowDetailsFragment
import net.dejanjokic.mediadb.util.Constants.UI.KEY_TV_FRAGMENT
import net.dejanjokic.mediadb.util.Constants.UI.KEY_TV_SHOW_QUERY
import net.dejanjokic.mediadb.util.ext.gone
import net.dejanjokic.mediadb.util.ext.visible
import timber.log.Timber
import javax.inject.Inject

class TvShowListFragment : Fragment(), TvShowListContract.View {

    @Inject lateinit var presenter: TvShowListContract.Presenter

    private val tvShowAdapter = TvShowAdapter {
        showTvShowDetails(it.id)
    }

    companion object {
        fun newInstance(query: String?) = TvShowListFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_TV_SHOW_QUERY, query)
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_tv_show_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attach(this)

        presenter.getTvShows(arguments?.getString(KEY_TV_SHOW_QUERY))

        recyclerViewTvShows.apply {
            adapter = tvShowAdapter
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
        loadingAnimTvShowList?.visible()
        recyclerViewTvShows?.gone()
    }

    override fun hideLoading() {
        loadingAnimTvShowList?.gone()
        recyclerViewTvShows?.visible()
    }

    override fun showErrorMessage(errorMessage: String?) {
        MaterialDialog(context!!).show {
            message(text = errorMessage)
            positiveButton(R.string.retry) {
                presenter.getTvShows(arguments?.getString(KEY_TV_SHOW_QUERY))
            }
        }
        Timber.e(errorMessage)
    }

    override fun showTvShows(tvShows: List<TvShow>) {
        tvShowAdapter.submitList(tvShows)
    }

    override fun showTvShowDetails(tvShowId: Long) {
        activity?.supportFragmentManager!!.beginTransaction()
            .replace(R.id.containerMain, TvShowDetailsFragment.newInstance(tvShowId))
            .addToBackStack(KEY_TV_FRAGMENT)
            .commit()
    }
}