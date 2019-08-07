package de.kleinelamas.svbrockscheid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import de.kleinelamas.svbrockscheid.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private val icons8Link = "https://icons8.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityAboutBinding>(this, R.layout.activity_about)
        binding.handler = this
    }


    fun onIcons8LinkClicked(view: View) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(icons8Link)
        intent.addCategory(Intent.CATEGORY_BROWSABLE)
        startActivity(intent)
    }
}
