package de.kleinelamas.svbrockscheid

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import de.kleinelamas.svbrockscheid.databinding.ListItemGameBinding
import de.kleinelamas.svbrockscheid.model.LeagueGame

/**
 * @author Matthias Kutscheid
 */
class LeagueAdapter: RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    var games: Array<LeagueGame>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    companion object {
        val headerView = 0
        val itemView = 1
    }

    override fun getItemCount(): Int {
        games?.let { games -> return games.size }
        return 0
    }

    override fun getItemViewType(position: Int): Int {
        return itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(ListItemGameBinding.inflate(LayoutInflater.from(parent?.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.binding?.game = games?.get(position)
    }

    class ViewHolder(val binding: ListItemGameBinding) : RecyclerView.ViewHolder(binding.root)
}