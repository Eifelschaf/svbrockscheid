package de.kleinelamas.svbrockscheid.fragments

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import de.kleinelamas.svbrockscheid.PlayerActivity
import de.kleinelamas.svbrockscheid.R
import de.kleinelamas.svbrockscheid.SVBApp
import de.kleinelamas.svbrockscheid.TeamAdapter
import de.kleinelamas.svbrockscheid.helpers.SpacesItemDecoration
import de.kleinelamas.svbrockscheid.model.Player
import de.kleinelamas.svbrockscheid.model.TeamLiveData
import kotlinx.android.synthetic.main.fragment_team.*
import kotlinx.android.synthetic.main.fragment_team.view.*
import javax.inject.Inject
import android.util.Pair as UtilPair

/**
 * @author Matthias Kutscheid
 */
class TeamFragment : Fragment(), TeamAdapter.SelectionListener {
    @Inject lateinit var teamData: TeamLiveData
    private val adapter: TeamAdapter = TeamAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        SVBApp.component.inject(this)
        super.onCreate(savedInstanceState)
        teamData.observe(this, Observer { team ->
            team?.let {
                adapter.teamHolder = team
                swipeLayout.isRefreshing = false
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_team, container, false)
        val refreshLayout: SwipeRefreshLayout? = view?.swipeLayout
        refreshLayout?.setOnRefreshListener {
            // get the data
            teamData.refresh()
        }
        view?.recyclerView?.adapter = adapter
        view?.recyclerView?.addItemDecoration(SpacesItemDecoration(resources.getDimensionPixelSize(R.dimen.spacing)))
        return view
    }

    override fun onPlayerSelected(player: Player, view: View) {
        //TODO: present player details
        Log.d("player", "Selected")
        var detailIntent = Intent(activity, PlayerActivity::class.java)
        val options = ActivityOptions.makeSceneTransitionAnimation(activity,
                UtilPair.create(view.findViewById(R.id.image), "player_image"),
                UtilPair.create(view.findViewById(R.id.playerName), "player_name"))
        detailIntent.putExtra("PLAYER", player)
        startActivity(detailIntent, options.toBundle())
    }
}