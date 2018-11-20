package com.anriku.cherryplayback

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

/**
 * Created by anriku on 2018/11/1.
 */

class BaseApp: Application() {

    companion object {
        const val MUSIC_SERVICE_CHANNEL_ID = "com.anriku.music_service_channel_id"

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }


    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        createNotificationChannel()
    }

    /**
     * This method is used to create the channel of the notification.
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(NotificationManager::class.java)
            // 这里IMPORTANCE_LOW的通知不会有默认的震动。然后这里需要特别注意的就是createNotificationChannel后，在App安装后即使
            // 修改了notificationChannel的配置，也不会发生改变。
            val notificationChannel = NotificationChannel(MUSIC_SERVICE_CHANNEL_ID, "CherryPlaybackChannel",
                NotificationManager.IMPORTANCE_LOW)
            manager.createNotificationChannel(notificationChannel)
        }
    }

}