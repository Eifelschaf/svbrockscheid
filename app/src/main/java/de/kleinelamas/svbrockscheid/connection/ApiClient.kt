package de.kleinelamas.svbrockscheid.connection

import de.kleinelamas.svbrockscheid.model.InfoNachricht
import de.kleinelamas.svbrockscheid.model.LeagueGame
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author Matthias Kutscheid
 */
interface ApiClient {
    @GET("/app.php")
    fun getOverView(): String
    @GET("/kreispokal.json")
    fun getKreispokalGames(): Call<ArrayList<LeagueGame>>
    @GET("/kreisliga1.json")
    fun getKreisliga1Games(): Call<ArrayList<LeagueGame>>
    @GET("/kreisliga2.json")
    fun getKreisliga2Games(): Call<ArrayList<LeagueGame>>
    @GET("/nachrichten.json")
    fun getNews(): ArrayList<InfoNachricht>
}