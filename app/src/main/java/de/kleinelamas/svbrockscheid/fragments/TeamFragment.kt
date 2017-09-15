package de.kleinelamas.svbrockscheid.fragments

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.kleinelamas.svbrockscheid.R
import de.kleinelamas.svbrockscheid.SVBApp
import de.kleinelamas.svbrockscheid.TeamAdapter
import de.kleinelamas.svbrockscheid.helpers.SpacesItemDecoration
import de.kleinelamas.svbrockscheid.model.TeamLiveData
import kotlinx.android.synthetic.main.fragment_team.view.*
import javax.inject.Inject

/**
 * @author Matthias Kutscheid
 */
class TeamFragment : LifecycleFragment() {

    @Inject lateinit var teamData: TeamLiveData
    private val adapter: TeamAdapter = TeamAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        SVBApp.component.inject(this)
        super.onCreate(savedInstanceState)
        teamData.observe(this, Observer { team ->
            team?.let {
                adapter.teamHolder = team
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater?.inflate(R.layout.fragment_team, container, false)
        val refreshLayout: SwipeRefreshLayout? = view?.swipeLayout
        refreshLayout?.setOnRefreshListener {
            // TODO: get the data
        }
        view?.recyclerView?.adapter = adapter
        view?.recyclerView?.addItemDecoration(SpacesItemDecoration(resources.getDimensionPixelSize(R.dimen.spacing)))
        return view
    }
}