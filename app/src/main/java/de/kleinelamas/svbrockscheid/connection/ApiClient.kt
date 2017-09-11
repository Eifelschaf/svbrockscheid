package de.kleinelamas.svbrockscheid.connection

import de.kleinelamas.svbrockscheid.model.LeagueGame
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

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
    @GET("register.php")
    fun sendPushToken(@Query("key") token: String?): Call<String>
}