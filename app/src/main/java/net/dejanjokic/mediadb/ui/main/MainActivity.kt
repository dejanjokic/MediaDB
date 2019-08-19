package net.dejanjokic.mediadb.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.dejanjokic.mediadb.R
import net.dejanjokic.mediadb.ui.DataType
import net.dejanjokic.mediadb.ui.movie.list.MovieListFragment
import net.dejanjokic.mediadb.ui.tv.list.TvShowListFragment
import timber.log.Timber

class MainActivity : AppCompatActivity(), MainContract.View {

    private var dataType: DataType = DataType.MOVIES

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(bottomAppBar)

        fabSearch.setOnClickListener {
            BottomSearchFragment().apply {
                show(supportFragmentManager, this.tag)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getData(dataType)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                BottomNavigationDrawerFragment().apply {
                    show(supportFragmentManager, this.tag)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showErrorMessage(errorMessage: String?) {
        Timber.e(errorMessage)
    }

    override fun showMovies(query: String?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerMain, MovieListFragment.newInstance(query))
            .commit()
    }

    override fun showTvShows(query: String?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerMain, TvShowListFragment.newInstance(query))
            .commit()
    }

    override fun getData(type: DataType?, query: String?) {

        when (type) {
            DataType.MOVIES -> {
                Timber.d("Showing $type for query: $query")
                dataType = type
                showMovies(query)
            }
            DataType.TV -> {
                Timber.d("Showing $type for $query")
                dataType = type
                showTvShows(query)
            }
            else -> getData(dataType, query)
        }
    }
}