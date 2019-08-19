package net.dejanjokic.mediadb.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottomsheet.*
import net.dejanjokic.mediadb.R
import net.dejanjokic.mediadb.ui.DataType

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    private lateinit var listener: MainContract.View

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MainContract.View) listener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?
                              , savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_bottomsheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navMovies -> {
                    listener.getData(type = DataType.MOVIES)
                    dismiss()
                }
                R.id.navTv -> {
                    listener.getData(type = DataType.TV)
                    dismiss()
                }
            }
            true
        }
    }
}