package de.kleinelamas.svbrockscheid

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import de.kleinelamas.svbrockscheid.connection.ApiClient
import de.kleinelamas.svbrockscheid.databinding.ListItemPlayerBinding
import de.kleinelamas.svbrockscheid.glide.GlideApp
import de.kleinelamas.svbrockscheid.model.Player
import de.kleinelamas.svbrockscheid.model.TeamHolder
import javax.inject.Inject

/**
 * @author Matthias Kutscheid
 */
class TeamAdapter : RecyclerView.Adapter<ViewHolder>() {

    init {
        SVBApp.component.inject(this)
    }

    @Inject lateinit var apiClient: ApiClient

    var teamHolder: TeamHolder? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        var count = 0
        teamHolder?.let {
            it.getTorhueter()?.let { count += it.size }
            it.getAbwehr()?.let { count += it.size }
            it.getMittelfeld()?.let { count += it.size }
            it.getAngriff()?.let { count += it.size }
            it.getTrainer()?.let { count += it.size }
        }
        return count
    }

    private fun getItem(position: Int): PlayerData? {
        var remainingPosition = position
        var player: Player? = null
        var playerPosition: String? = null

        // find the correct player
        teamHolder?.let {
            val calculatePositionOrReturn: (Array<Player>) -> Unit = {
                if (remainingPosition > -1) {
                    if (remainingPosition < it.size) {
                        player = it[remainingPosition]
                    }
                    remainingPosition -= it.size
                }
            }
            playerPosition = "Torhüter"
            it.getTorhueter()?.let(calculatePositionOrReturn)
            if (remainingPosition > -1) {
                playerPosition = "Abwehr"
            }
            it.getAbwehr()?.let(calculatePositionOrReturn)
            if (remainingPosition > -1) {
                playerPosition = "Mittelfeld"
            }
            it.getMittelfeld()?.let(calculatePositionOrReturn)
            if (remainingPosition > -1) {
                playerPosition = "Angriff"
            }
            it.getAngriff()?.let(calculatePositionOrReturn)
            if (remainingPosition > -1) {
                playerPosition = "Trainer"
            }
            it.getTrainer()?.let(calculatePositionOrReturn)
        }
        player?.let { player ->
            playerPosition?.let { playerPosition ->
                return PlayerData(player, playerPosition)
            }
        }
        return null
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        getItem(position)?.let { (player, position) ->
            holder?.let { holder ->
                holder.binding.player = player
                holder.binding.position = position
                GlideApp.with(holder.itemView).load(BuildDependentConstants.URL + "bilder/team/" + player.bild).placeholder(R.drawable.ic_player).into(holder.binding.image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(ListItemPlayerBinding.inflate(LayoutInflater.from(parent?.context), parent, false))
    }
}

class ViewHolder(val binding: ListItemPlayerBinding) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.image.layoutParams.width = binding.image.context.resources.displayMetrics.widthPixels / 2
    }
}

private data class PlayerData(val player: Player, val position: String)