package de.kleinelamas.svbrockscheid

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.AndroidInjection
import de.kleinelamas.svbrockscheid.fragments.GamesFragment
import de.kleinelamas.svbrockscheid.fragments.TeamFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        when (item.itemId) {
            R.id.navigation_league -> {
                if (currentFragment !is GamesFragment) {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragmentContainer, GamesFragment(), null)
                    if (currentFragment != null) {
                        transaction.addToBackStack("")
                    }
                    transaction.commit()
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_players -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainer, TeamFragment(), null)
                if (currentFragment != null) {
                    transaction.addToBackStack("")
                }
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_league
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val result = super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.info, menu)
        return result
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.about -> {
                // display the about screen
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
