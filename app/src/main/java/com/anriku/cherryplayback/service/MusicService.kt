package com.anriku.cherryplayback.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.anriku.cherryplayback.BaseApp
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.config.LAST_PLAY_INDEX
import com.anriku.cherryplayback.ui.ControlActivity
import com.anriku.cherryplayback.config.MUSIC_NOTIFICATION_ID
import com.anriku.cherryplayback.utils.extensions.setSPValue

/**
 * 进行音乐后台播放的服务
 *
 * Created by anriku on 2018/11/1.
 */
class MusicService : Service() {

    companion object {
        const val TAG = "MusicService"
    }

    private val mMusicBinder: MusicBinder by lazy(LazyThreadSafetyMode.NONE) { MusicBinder(this) }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!mMusicBinder.isPlaying()) {
            val notificationIntent = Intent(this, ControlActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                this, 0, notificationIntent, 0
            )
            // 创建通知
            val notification = NotificationCompat.Builder(
                this,
                BaseApp.MUSIC_SERVICE_CHANNEL_ID
            ).setContentTitle("CherryPlayback")
                .setContentText("song")
                .setSmallIcon(R.drawable.ic_music_placeholder)
                .setContentIntent(pendingIntent)
                .build()

            // 将服务置于前台
            startForeground(MUSIC_NOTIFICATION_ID, notification)
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mMusicBinder
    }

    override fun onDestroy() {
        mMusicBinder.release()
        this.setSPValue({
            putInt(LAST_PLAY_INDEX, mMusicBinder.getCurrentPlayIndex())
        })
        stopSelf()
    }
}