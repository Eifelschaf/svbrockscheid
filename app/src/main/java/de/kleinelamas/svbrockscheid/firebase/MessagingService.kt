package de.kleinelamas.svbrockscheid.firebase

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import de.kleinelamas.svbrockscheid.SVBApp
import de.kleinelamas.svbrockscheid.connection.ApiClient
import javax.inject.Inject

/**
 * @author Matthias Kutscheid
 */
class MessagingService: FirebaseMessagingService() {

    @Inject
    lateinit var apiClient: ApiClient

    init {
        SVBApp.component.inject(this)
    }

    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        val pushResponse = apiClient.sendPushToken(newToken)
        //ignore the response for now
        pushResponse.execute()
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.data.let {
            if (it.isNotEmpty()) {
                Log.d("NOTIFICATION!", "data received: $it")
            }
        }

        message.notification?.body?.let {
            Log.d("NOTIFICATION!", "notificationBody: $it")
        }
    }
}