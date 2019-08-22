package de.kleinelamas.svbrockscheid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import de.kleinelamas.svbrockscheid.databinding.ActivityPlayerDetailBinding
import de.kleinelamas.svbrockscheid.glide.GlideApp
import de.kleinelamas.svbrockscheid.model.Player
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerActivity : AppCompatActivity() {

    private lateinit var player: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        player = intent.getParcelableExtra("PLAYER")
        val binding: ActivityPlayerDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_player_detail)
        binding.player = player
        GlideApp.with(layout).load(BuildDependentConstants.URL + "bilder/team/" + player.bild).placeholder(R.drawable.ic_player).into(imageView)
    }
}