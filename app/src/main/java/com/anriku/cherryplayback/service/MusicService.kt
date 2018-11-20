package com.anriku.cherryplayback.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.anriku.cherryplayback.BaseApp
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.broadcast.CherryBroadcastReceiver
import com.anriku.cherryplayback.config.MUSIC_NOTIFICATION_ID
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.network.ApiGenerate
import com.anriku.cherryplayback.network.ImageUrl
import com.anriku.cherryplayback.network.QQMusicService
import com.anriku.cherryplayback.rxjava.ExecuteOnceObserver
import com.anriku.cherryplayback.ui.MainActivity
import com.anriku.cherryplayback.utils.GlideUtil
import com.anriku.cherryplayback.utils.LogUtil
import com.anriku.cherryplayback.utils.NotificationUtil
import com.anriku.cherryplayback.utils.PlaybackInfoListener
import com.anriku.cherryplayback.utils.extensions.errorHandler
import com.anriku.cherryplayback.utils.extensions.setOnclickListener
import com.anriku.cherryplayback.utils.extensions.setSchedulers

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
        const val BROADCAST_ACTION = "broadcast_action"

        const val TAG = "MusicService"
    }

    private val mPlayAndPauseIcons: List<Int> by lazy(LazyThreadSafetyMode.NONE) {
        listOf(R.drawable.ic_pause, R.drawable.ic_play)
    }
    private lateinit var mNotificationUtil: NotificationUtil
    private val mQQMusicService: QQMusicService by lazy(LazyThreadSafetyMode.NONE) {
        ApiGenerate.getGsonApiService(QQMusicService::class.java)
    }


    private var mIsServiceFirstCreate: Boolean = true
    private val mMusicBinder: MusicBinder by lazy(LazyThreadSafetyMode.NONE) { MusicBinder(this) }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        if (mIsServiceFirstCreate) {

            addNotification()
            mIsServiceFirstCreate = false
        }

        mMusicBinder.playSet(intent)

        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * 添加通知并将服务置于前台
     */
    private fun addNotification() {
        // 给通知添加观察者
        mMusicBinder.addPlaybackInfoListener(NotificationPlayInfoCallback())

        mNotificationUtil = NotificationUtil(this)
        val notification = mNotificationUtil.createNotification(
            MainActivity::class.java,
            R.layout.music_notification_small, R.layout.music_notification_large, BaseApp.MUSIC_SERVICE_CHANNEL_ID
        )

        mNotificationUtil.setActionOnRemoteViews(MUSIC_NOTIFICATION_ID, onSmallActions = {
            it.setOnclickListener(this, R.id.iv_play_or_pause, CherryBroadcastReceiver.ACTION_PLAY_OR_PAUSE)
            it.setOnclickListener(this, R.id.iv_previous, CherryBroadcastReceiver.ACTION_PREVIOUS)
            it.setOnclickListener(this, R.id.iv_next, CherryBroadcastReceiver.ACTION_NEXT)
        }, onLargeActions = {
            it.setOnclickListener(this, R.id.iv_play_or_pause, CherryBroadcastReceiver.ACTION_PLAY_OR_PAUSE)
            it.setOnclickListener(this, R.id.iv_previous, CherryBroadcastReceiver.ACTION_PREVIOUS)
            it.setOnclickListener(this, R.id.iv_next, CherryBroadcastReceiver.ACTION_NEXT)
        })

        // 将服务置于前台
        startForeground(MUSIC_NOTIFICATION_ID, notification)
    }

    inner class NotificationPlayInfoCallback : PlaybackInfoListener() {
        override fun onLoadMedia(song: Song) {

            LogUtil.d(TAG, song.title.toString())

            if (song.musicType == Song.ONLINE) {
                GlideUtil.getBitmap(this@MusicService, ImageUrl.getAlbumImageUrl(
                    song.albumId?.toLong()
                        ?: -1
                ), onGet = { bitmap ->
                    mNotificationUtil.setActionOnRemoteViews(MUSIC_NOTIFICATION_ID, onLargeActions = {
                        it.setImageViewBitmap(R.id.iv_song, bitmap)
                        it.setTextViewText(R.id.tv_artist, song.artist)
                        it.setTextViewText(R.id.tv_song_name, song.title)
                    }, onSmallActions = {
                        it.setImageViewBitmap(R.id.iv_song, bitmap)
                        it.setTextViewText(R.id.tv_artist, song.artist)
                        it.setTextViewText(R.id.tv_song_name, song.title)
                    })
                })
            } else {
                val searchKey = "${song.title}-${song.artist}"

                mQQMusicService
                    .search(searchKey, 1, 1)
                    .setSchedulers()
                    .errorHandler()
                    .subscribe(ExecuteOnceObserver(onExecuteOnceNext = { searchResult ->

                        GlideUtil.getBitmap(this@MusicService, ImageUrl
                            .getAlbumImageUrl(searchResult.data.song.list[0].album.id.toLong(), 500)
                            , onGet = { bitmap ->
                                mNotificationUtil.setActionOnRemoteViews(MUSIC_NOTIFICATION_ID, onLargeActions = {
                                    it.setImageViewBitmap(R.id.iv_song, bitmap)
                                    it.setTextViewText(R.id.tv_artist, song.artist)
                                    it.setTextViewText(R.id.tv_song_name, song.title)
                                }, onSmallActions = {
                                    it.setImageViewBitmap(R.id.iv_song, bitmap)
                                    it.setTextViewText(R.id.tv_artist, song.artist)
                                    it.setTextViewText(R.id.tv_song_name, song.title)
                                })

                            })
                    }))
            }
        }

        override fun onDurationChanged(duration: Int) {}

        override fun onPositionChanged(position: Int) {}

        override fun onStateChange(state: Int) {
            when (state) {
                PlaybackInfoListener.PAUSE, PlaybackInfoListener.COMPLETE,
                PlaybackInfoListener.INVALID, PlaybackInfoListener.RESET -> {
                    mNotificationUtil.setActionOnRemoteViews(MUSIC_NOTIFICATION_ID, onSmallActions = {
                        it.setImageViewResource(R.id.iv_play_or_pause, mPlayAndPauseIcons[1])
                    }, onLargeActions = {
                        it.setImageViewResource(R.id.iv_play_or_pause, mPlayAndPauseIcons[1])
                    })
                }
                else -> {
                    mNotificationUtil.setActionOnRemoteViews(MUSIC_NOTIFICATION_ID, onSmallActions = {
                        it.setImageViewResource(R.id.iv_play_or_pause, mPlayAndPauseIcons[0])
                    }, onLargeActions = {
                        it.setImageViewResource(R.id.iv_play_or_pause, mPlayAndPauseIcons[0])
                    })
                }
            }
        }

        override fun onComplete() {}

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