package de.kleinelamas.svbrockscheid.model

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import de.kleinelamas.svbrockscheid.SVBApp
import de.kleinelamas.svbrockscheid.connection.ApiClient
import retrofit2.Call
import javax.inject.Inject

/**
 * @author Matthias Kutscheid
 */
class GameLiveData : LiveData<LeagueHolder>() {

    var task: AsyncTask<Void, Void, LeagueHolder>? = null

    init {
        SVBApp.component.inject(this)
    }

    @Inject lateinit var apiClient: ApiClient

    override fun onActive() {
        super.onActive()
        refresh()
    }

    fun refresh() {
        task = object : AsyncTask<Void, Void, LeagueHolder>() {
            override fun doInBackground(vararg p0: Void?): LeagueHolder {
                //get all league games
                return LeagueHolder(handleGames(apiClient.getKreisliga1Games()), handleGames(apiClient.getKreisliga2Games()), handleGames(apiClient.getKreispokalGames()))
            }

            override fun onPostExecute(result: LeagueHolder) {
                super.onPostExecute(result)
                value = result
            }
        }
        task?.execute(null)
    }

    private fun handleGames(call: Call<ArrayList<LeagueGame>>): Array<LeagueGame>{
        val response = call.execute()
        response.body()?.let { return it.toTypedArray() }
        return arrayOf()
    }
}