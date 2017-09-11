package de.kleinelamas.svbrockscheid.firebase

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import de.kleinelamas.svbrockscheid.SVBApp
import de.kleinelamas.svbrockscheid.connection.ApiClient
import javax.inject.Inject

/**
 * @author Matthias Kutscheid
 */
class FirebaseService: FirebaseInstanceIdService() {

    @Inject lateinit var apiClient: ApiClient

    init {
        SVBApp.component.inject(this)
    }
    override fun onTokenRefresh() {
        super.onTokenRefresh()
        val refreshToken = FirebaseInstanceId.getInstance().token
        val pushResponse = apiClient.sendPushToken(refreshToken)
        //ignore the response for now
        pushResponse.execute()
    }
}