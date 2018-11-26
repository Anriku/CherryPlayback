package com.anriku.cherryplayback.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.anriku.cherryplayback.BaseApp
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.config.MUSIC_NOTIFICATION_ID
import com.anriku.cherryplayback.config.MUSIC_SWITCH_INTERVAL
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.network.ApiGenerate
import com.anriku.cherryplayback.network.ImageUrl
import com.anriku.cherryplayback.network.QQMusicService
import com.anriku.cherryplayback.rxjava.ExecuteOnceObserver
import com.anriku.cherryplayback.ui.MainActivity
import com.anriku.cherryplayback.utils.GlideUtil
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

        const val ACTION_PLAY_OR_PAUSE = "com.anriku.cherryplayback.PLAY_OR_PAUSE"
        const val ACTION_PREVIOUS = "com.anriku.cherryplayback.PLAY_PREVIOUS"
        const val ACTION_NEXT = "com.anriku.cherryplayback.PLAY_NEXT"

        const val TAG = "MusicService"
    }

    private lateinit var mNotificationUtil: NotificationUtil
    private val mQQMusicService: QQMusicService by lazy(LazyThreadSafetyMode.NONE) {
        ApiGenerate.getGsonApiService(QQMusicService::class.java)
    }
    private var mLastTime = 0L


    private var mIsServiceFirstCreate: Boolean = true
    private val mMusicBinder: MusicBinder by lazy(LazyThreadSafetyMode.NONE) { MusicBinder(this) }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (mIsServiceFirstCreate) {

            addNotification()
            mIsServiceFirstCreate = false
        }

        // 防止连续切换
        val time = System.currentTimeMillis()
        if (time - mLastTime > MUSIC_SWITCH_INTERVAL) {
            mMusicBinder.playSet(intent)
            mLastTime = time
        }
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
            it.setOnclickListener(this, R.id.iv_play_or_pause, MusicService.ACTION_PLAY_OR_PAUSE)
            it.setOnclickListener(this, R.id.iv_previous, MusicService.ACTION_PREVIOUS)
            it.setOnclickListener(this, R.id.iv_next, MusicService.ACTION_NEXT)
        }, onLargeActions = {
            it.setOnclickListener(this, R.id.iv_play_or_pause, MusicService.ACTION_PLAY_OR_PAUSE)
            it.setOnclickListener(this, R.id.iv_previous, MusicService.ACTION_PREVIOUS)
            it.setOnclickListener(this, R.id.iv_next, MusicService.ACTION_NEXT)
        })

        // 将服务置于前台
        startForeground(MUSIC_NOTIFICATION_ID, notification)
    }

    inner class NotificationPlayInfoCallback : PlaybackInfoListener() {
        override fun onLoadMedia(song: Song) {

            mNotificationUtil.setActionOnRemoteViews(MUSIC_NOTIFICATION_ID, onLargeActions = {
                it.setTextViewText(R.id.tv_artist, song.artist)
                it.setTextViewText(R.id.tv_song_name, song.title)
            }, onSmallActions = {
                it.setTextViewText(R.id.tv_artist, song.artist)
                it.setTextViewText(R.id.tv_song_name, song.title)
            })

            if (song.musicType == Song.ONLINE) {
                GlideUtil.getBitmap(this@MusicService, ImageUrl.getAlbumImageUrl(
                    song.albumId?.toLong()
                        ?: -1
                ), onGet = { bitmap ->
                    mNotificationUtil.setActionOnRemoteViews(MUSIC_NOTIFICATION_ID, onLargeActions = {
                        it.setImageViewBitmap(R.id.iv_song, bitmap)
                    }, onSmallActions = {
                        it.setImageViewBitmap(R.id.iv_song, bitmap)
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
                                }, onSmallActions = {
                                    it.setImageViewBitmap(R.id.iv_song, bitmap)
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
                        it.setImageViewResource(R.id.iv_play_or_pause, R.drawable.ic_play)
                    }, onLargeActions = {
                        it.setImageViewResource(R.id.iv_play_or_pause, R.drawable.ic_play)
                    })
                }
                else -> {
                    mNotificationUtil.setActionOnRemoteViews(MUSIC_NOTIFICATION_ID, onSmallActions = {
                        it.setImageViewResource(R.id.iv_play_or_pause, R.drawable.ic_pause)
                    }, onLargeActions = {
                        it.setImageViewResource(R.id.iv_play_or_pause, R.drawable.ic_pause)
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