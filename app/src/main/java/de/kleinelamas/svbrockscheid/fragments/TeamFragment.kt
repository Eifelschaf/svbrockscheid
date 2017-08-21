package de.kleinelamas.svbrockscheid.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.kleinelamas.svbrockscheid.R
import kotlinx.android.synthetic.main.fragment_team.view.*

/**
 * @author Matthias Kutscheid
 */
class TeamFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater?.inflate(R.layout.fragment_team, container, false)
        val refreshLayout: SwipeRefreshLayout? = view?.swipeLayout
        refreshLayout?.setOnRefreshListener {
            // TODO: get the data
        }
        return view
    }
}