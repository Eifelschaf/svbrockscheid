package de.kleinelamas.svbrockscheid

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.kleinelamas.svbrockscheid.databinding.ListItemGameBinding
import de.kleinelamas.svbrockscheid.model.LeagueGame
import de.kleinelamas.svbrockscheid.model.LeagueHolder
import kotlinx.android.synthetic.main.list_item_title.view.*

/**
 * @author Matthias Kutscheid
 */
class LeagueAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var leagueHolder: LeagueHolder? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    companion object {
        val headerView = 0
        val itemView = 1
    }

    override fun getItemCount(): Int {
        var count = 0
        leagueHolder?.let { leagueHolder ->
            if (!leagueHolder.kreisliga1.isEmpty()) { count += leagueHolder.kreisliga1.size + 1}
            if (!leagueHolder.kreisliga2.isEmpty()) { count += leagueHolder.kreisliga2.size + 1}
            if (!leagueHolder.kreispokal.isEmpty()) { count += leagueHolder.kreispokal.size + 1} }

        return count
    }

    private fun getItem(position: Int): LeagueGame? {
        return when(position) {
            in 1..leagueHolder!!.kreisliga1.size -> leagueHolder!!.kreisliga1[position]
            in leagueHolder!!.kreisliga1.size + 1..leagueHolder!!.kreisliga2.size -> leagueHolder!!.kreisliga2[position]
            in leagueHolder!!.kreisliga2.size + 1..leagueHolder!!.kreispokal.size -> leagueHolder!!.kreispokal[position]
            else -> null
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0,
            leagueHolder!!.kreisliga1.size,
            leagueHolder!!.kreisliga1.size + 1 + leagueHolder!!.kreisliga2.size-> headerView
            else -> itemView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            itemView -> ViewHolder(ListItemGameBinding.inflate(LayoutInflater.from(parent?.context), parent, false))
            else -> TitleHolder(LayoutInflater.from(parent?.context).inflate(R.layout.list_item_title, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is ViewHolder) {
            holder.binding.game = getItem(position)
        } else if (holder is TitleHolder) {
            holder.title.text = "TITLE"
        }
    }

    class ViewHolder(val binding: ListItemGameBinding) : RecyclerView.ViewHolder(binding.root)

    class TitleHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView

        init {
            title = view.title
        }
    }
}