package de.kleinelamas.svbrockscheid.model

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import android.util.Log
import de.kleinelamas.svbrockscheid.SVBApp
import de.kleinelamas.svbrockscheid.connection.ApiClient
import retrofit2.Call
import javax.inject.Inject

/**
 * @author Matthias Kutscheid
 */
class GameLiveData : LiveData<Array<LeagueGame>>() {

    init {
        SVBApp.component.inject(this)
    }

    @Inject lateinit var apiClient: ApiClient

    override fun onActive() {

        super.onActive()
        val task = object : AsyncTask<Void, Void, Array<LeagueGame>>(){
            override fun doInBackground(vararg p0: Void?): Array<LeagueGame> {
                //get all league games
                val kreisLiga1 = handleGames(apiClient.getKreisliga1Games())
                val kreisLiga2 = handleGames(apiClient.getKreisliga2Games())
                val kreisPokal = handleGames(apiClient.getKreispokalGames())
                val gameListing = ArrayList<LeagueGame>()
                kreisLiga1?.let { kreisLiga1 -> gameListing.addAll(kreisLiga1) }
                kreisLiga2?.let { kreisLiga2 -> gameListing.addAll(kreisLiga2) }
                kreisPokal?.let { kreisPokal -> gameListing.addAll(kreisPokal) }
                return gameListing.toTypedArray()
            }

            override fun onPostExecute(result: Array<LeagueGame>?) {
                super.onPostExecute(result)
                value = result
            }

        }
        task.execute(null)
    }

    private fun handleGames(call: Call<ArrayList<LeagueGame>>): ArrayList<LeagueGame>?{
        val response = call.execute()
        Log.e("TAG", response.raw().body().toString())
        return response.body()
    }
}