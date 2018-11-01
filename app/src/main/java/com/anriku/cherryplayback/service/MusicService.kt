package com.anriku.cherryplayback.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.anriku.cherryplayback.BaseApp
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.adapter.CherryPlayer
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.ui.ControlActivity
import com.anriku.cherryplayback.utils.LogUtil
import com.anriku.cherryplayback.utils.PlaybackInfoListener
import com.anriku.cherryplayback.utils.PlayerAdapter
import com.anriku.cherryplayback.utils.notification.MUSIC_NOTIFICATION_ID

/**
 * Created by anriku on 2018/11/1.
 */

class MusicService : Service() {

    var songs: List<Song> = mutableListOf()
    var currentPlayIndex: Int = 0

    companion object {
        const val TAG = "MusicService"
        const val SEQUENCE_PLAY = 0
        const val RANDOM_PLAY = 1
        const val SINGLE_PLAY = 2
    }

    private val mPlayerAdapter: PlayerAdapter by lazy(LazyThreadSafetyMode.NONE) {
        CherryPlayer(application)
    }

    private val mMusicBinder: MusicBinder by lazy(LazyThreadSafetyMode.NONE) {
        MusicBinder()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val notificationIntent = Intent(this, ControlActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0)
        val notification = NotificationCompat.Builder(this,
                BaseApp.MUSIC_SERVICE_CHANNEL_ID)
                .setContentTitle("CherryPlayback")
                .setContentText("song")
                .setSmallIcon(R.drawable.ic_music)
                .setContentIntent(pendingIntent)
                .build()
        startForeground(MUSIC_NOTIFICATION_ID, notification)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mMusicBinder
    }

    override fun onDestroy() {
        stopSelf()
    }

    inner class MusicBinder : Binder() {

        fun setSongs(songs: List<Song>) {
            this@MusicService.songs = songs
        }

        fun getSongs(): List<Song> = songs

        fun addPlaybackInfoListener(listener: PlaybackInfoListener) {
            mPlayerAdapter.addPlaybackInfoListener(listener)
        }

        fun loadAnotherMusic(pattern: Int, isNext: Boolean = true) {
            when (pattern) {
                SEQUENCE_PLAY -> {
                    if (isNext) {
                        currentPlayIndex++
                    } else {
                        currentPlayIndex--
                    }
                    loadLocalMedia(currentPlayIndex)
                }
                RANDOM_PLAY -> {

                }
                SINGLE_PLAY -> {

                }
            }
        }

        fun loadLocalMedia(position: Int) {
            currentPlayIndex = position
            songs[position].data?.let {
                loadMedia(it, false)
            }
        }

        fun loadMedia(resourcePath: String, isOnlineData: Boolean) {
            mPlayerAdapter.loadMedia(resourcePath, isOnlineData)
        }

        fun play() {
            mPlayerAdapter.play()
        }

        fun pause() {
            mPlayerAdapter.pause()
        }

        fun reset() {
            mPlayerAdapter.reset()
        }

        fun isPlaying(): Boolean = mPlayerAdapter.isPlaying()

        fun seekTo(position: Int) {
            mPlayerAdapter.seekTo(position)
        }

        fun release() {
            mPlayerAdapter.release()
        }
    }
}