package com.anriku.cherryplayback

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

/**
 * Created by anriku on 2018/11/1.
 */

class BaseApp: Application() {

    companion object {
        const val MUSIC_SERVICE_CHANNEL_ID = "com.anriku.music_service_channel_id"
    }

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    /**
     * This method is used to create the channel of the notification.
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                    MUSIC_SERVICE_CHANNEL_ID,
                    "CherryPlaybackChannel",
                    NotificationManager.IMPORTANCE_HIGH)
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(notificationChannel)
        }
    }

}