package de.kleinelamas.svbrockscheid.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.kleinelamas.svbrockscheid.LeagueAdapter
import de.kleinelamas.svbrockscheid.R
import de.kleinelamas.svbrockscheid.SVBApp
import de.kleinelamas.svbrockscheid.model.GameLiveData
import kotlinx.android.synthetic.main.fragment_games.*
import kotlinx.android.synthetic.main.fragment_games.view.*
import javax.inject.Inject


/**
 * @author Matthias Kutscheid
 */
class GamesFragment : Fragment() {

    @Inject lateinit var gameData : GameLiveData
    private val adapter: LeagueAdapter = LeagueAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        SVBApp.component.inject(this)
        super.onCreate(savedInstanceState)
        gameData.observe(this, Observer { games ->
            games?.let {
                adapter.leagueHolder = games
                swipeLayout.isRefreshing = false
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater?.inflate(R.layout.fragment_games, container, false)
        view?.swipeLayout?.setOnRefreshListener {
            // get the data
            gameData.refresh()
        }
        view?.recyclerView?.layoutManager = LinearLayoutManager(view?.recyclerView?.context, LinearLayoutManager.VERTICAL, false)
        view?.recyclerView?.adapter = adapter
        return view
    }
}