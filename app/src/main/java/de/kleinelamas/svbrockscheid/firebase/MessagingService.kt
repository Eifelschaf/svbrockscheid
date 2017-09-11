package de.kleinelamas.svbrockscheid.firebase

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * @author Matthias Kutscheid
 */
class MessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage?) {
        super.onMessageReceived(message)
        message?.data?.let {
            if (it.isNotEmpty()) {
                Log.d("NOTIFICATION!", "data received: $it")
            }
        }

        message?.notification?.body?.let {
            Log.d("NOTIFICATION!", "notificationBody: $it")
        }
    }
}