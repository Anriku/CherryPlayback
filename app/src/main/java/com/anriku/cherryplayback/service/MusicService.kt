package com.anriku.cherryplayback.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.anriku.cherryplayback.BaseApp
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.broadcast.CherryBroadcastReceiver
import com.anriku.cherryplayback.config.MUSIC_NOTIFICATION_ID
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.ui.MainActivity
import com.anriku.cherryplayback.utils.NotificationUtil
import com.anriku.cherryplayback.utils.PlaybackInfoListener
import com.anriku.cherryplayback.utils.extensions.setOnclickListener

/**
 * 进行音乐后台播放的服务
 *
 * Created by anriku on 2018/11/1.
 */
class MusicService : Service() {

    companion object {
        const val NOT_HAVE_INDEX = -1

        const val PLAY_INDEX = "play_index"
        const val SONGS = "songs"
        const val IS_ONLY_LOAD = "is_only_load"

        const val TAG = "MusicService"
    }

    private lateinit var mNotificationUtil: NotificationUtil
    private var mIsServiceFirstCreate: Boolean = true
    private val mMusicBinder: MusicBinder by lazy(LazyThreadSafetyMode.NONE) { MusicBinder(this) }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (mIsServiceFirstCreate) {

            mNotificationUtil = NotificationUtil()
            val notification = mNotificationUtil.createNotification(
                this, MainActivity::class.java, R.layout.music_notification_small,
                R.layout.music_notification_large, BaseApp.MUSIC_SERVICE_CHANNEL_ID
            )
            mNotificationUtil.setActionOnLargeRemoteViews {
                it.setOnclickListener(this, R.id.iv_play_or_pause, CherryBroadcastReceiver.ACTION_PLAY_OR_PAUSE)
                it.setOnclickListener(this, R.id.iv_previous, CherryBroadcastReceiver.ACTION_PREVIOUS)
                it.setOnclickListener(this, R.id.iv_next, CherryBroadcastReceiver.ACTION_NEXT)
            }

            mNotificationUtil.setActionOnSmallRemoteViews {
                it.setOnclickListener(this, R.id.iv_play_or_pause, CherryBroadcastReceiver.ACTION_PLAY_OR_PAUSE)
                it.setOnclickListener(this, R.id.iv_previous, CherryBroadcastReceiver.ACTION_PREVIOUS)
                it.setOnclickListener(this, R.id.iv_next, CherryBroadcastReceiver.ACTION_NEXT)
            }

            // 将服务置于前台
            startForeground(MUSIC_NOTIFICATION_ID, notification)

            mIsServiceFirstCreate = false
        }

        mMusicBinder.playSet(intent)
        return super.onStartCommand(intent, flags, startId)
    }

    inner class NotificationPlayInfoCallback : PlaybackInfoListener() {
        override fun onLoadMedia(song: Song) {
        }

        override fun onDurationChanged(duration: Int) {
        }

        override fun onPositionChanged(position: Int) {
        }

        override fun onStateChange(state: Int) {

        }

        override fun onComplete() {

        }

    }

    override fun onBind(intent: Intent?): IBinder? {
        return mMusicBinder
    }

    override fun onDestroy() {
        mMusicBinder.storeSongsAndIndex()
        mMusicBinder.release()
        stopSelf()
    }
}