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
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.ui.MainActivity
import com.anriku.cherryplayback.utils.extensions.setSPValue

/**
 * 进行音乐后台播放的服务
 *
 * Created by anriku on 2018/11/1.
 */
class MusicService : Service() {

    companion object {
        const val INIT_PLAYBACK = -1
        const val DO_NOTHING = -2

        const val PLAY_INDEX = "play_index"
        const val SONGS = "songs"
        const val IS_ONLINE = "is_online"

        const val TAG = "MusicService"
    }

    private var mIsServiceFirstCreate: Boolean = true
    private val mMusicBinder: MusicBinder by lazy(LazyThreadSafetyMode.NONE) { MusicBinder(this) }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (mIsServiceFirstCreate) {
            val notificationIntent = Intent(this, MainActivity::class.java)
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
            mIsServiceFirstCreate = false
        }

        playSet(intent)

        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * 用于对播放相关的内容设置
     *
     * @param intent startService所传入的intent
     */
    private fun playSet(intent: Intent) {
        Thread {
            val playIndex = intent.getIntExtra(PLAY_INDEX, DO_NOTHING)
            val songs = intent.getParcelableArrayListExtra<Song>(SONGS)
            val isOnline = intent.getBooleanExtra(IS_ONLINE, false)

            // 设置播放源
            songs?.let {
                mMusicBinder.setSongs(it, isOnline)
            }

            if (playIndex >= 0) {
                mMusicBinder.loadMediaByPosition(playIndex)
            }

        }.start()
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