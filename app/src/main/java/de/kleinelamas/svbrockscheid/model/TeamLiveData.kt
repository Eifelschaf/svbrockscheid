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
class TeamLiveData : LiveData<TeamHolder>() {

    init {
        SVBApp.component.inject(this)
    }

    @Inject lateinit var apiClient: ApiClient
    var task: AsyncTask<Void, Void, TeamHolder>? = null

    override fun onActive() {
        super.onActive()
        refresh()
    }

    fun refresh() {
        task = object : AsyncTask<Void, Void, TeamHolder>() {
            override fun doInBackground(vararg p0: Void?): TeamHolder? {
                //get all players
                return handleTeam(apiClient.getTeam())
            }

            override fun onPostExecute(result: TeamHolder?) {
                super.onPostExecute(result)
                value = result
            }

        }
        task?.execute()
    }

    private fun handleTeam(call: Call<HashMap<String, Array<Player>>>): TeamHolder? {
        val response = call.execute()
        Log.e("TAG", response.raw().body().toString())
        response.body()?.let { return TeamHolder(it) }
        return null
    }
}