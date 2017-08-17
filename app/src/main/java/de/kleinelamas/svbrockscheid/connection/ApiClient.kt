package de.kleinelamas.svbrockscheid.connection

import de.kleinelamas.svbrockscheid.model.InfoNachricht
import de.kleinelamas.svbrockscheid.model.LigaSpiel
import retrofit2.http.GET

/**
 * @author Matthias Kutscheid
 */
interface ApiClient {
    @GET("/app.php")
    fun getOverView(): String
    @GET("/kreispokal.json")
    fun getKreispokalGames(): Array<LigaSpiel>
    @GET("/kreisliga1.json")
    fun getKreisliga1Games(): Array<LigaSpiel>
    @GET("/kreisliga2.json")
    fun getKreisliga2Games(): Array<LigaSpiel>
    @GET("/nachrichten.json")
    fun getNews(): Array<InfoNachricht>
}