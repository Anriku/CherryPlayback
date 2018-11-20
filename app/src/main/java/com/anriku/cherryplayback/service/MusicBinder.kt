package com.anriku.cherryplayback.service

import android.content.Context
import android.content.Intent
import com.anriku.cherryplayback.adapter.CherryMusicPlayer
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.utils.IMusicBinder
import com.anriku.cherryplayback.utils.PlaybackInfoListener
import com.anriku.cherryplayback.adapter.PlayerAdapter
import com.anriku.cherryplayback.broadcast.CherryBroadcastReceiver
import com.anriku.cherryplayback.config.IS_HAVE_RECORD
import com.anriku.cherryplayback.config.IS_ONLINE
import com.anriku.cherryplayback.config.LAST_PLAY_INDEX
import com.anriku.cherryplayback.database.SongsDatabase
import com.anriku.cherryplayback.utils.extensions.setSPValue

/**
 * Created by anriku on 2018/11/2.
 */

/**
 * 用于与Activity或者Notification通信的Binder.并且还充当这Activity操作[PlayerAdapter]的中介
 */
class MusicBinder(private val mContext: Context) : IMusicBinder() {

    companion object {
        private const val TAG = "MusicBinder"
    }

    private val mPlayerAdapter: PlayerAdapter by lazy(LazyThreadSafetyMode.NONE) { CherryMusicPlayer(mContext) }
    private val mSongsDatabase: SongsDatabase? by lazy(LazyThreadSafetyMode.NONE) { SongsDatabase.getDatabase(mContext) }

    /**
     * 用于对播放相关的内容设置
     *
     * @param intent startService所传入的intent
     */
    override fun playSet(intent: Intent) {
        Thread {
            val playIndex = intent.getIntExtra(MusicService.PLAY_INDEX, MusicService.NOT_HAVE_INDEX)
            val songs = intent.getParcelableArrayListExtra<Song>(MusicService.SONGS)
            val isOnlyLoad = intent.getBooleanExtra(MusicService.IS_ONLY_LOAD, false)
            // 用于Notification的RemoteViews的点击事件响应
            val broadcastAction = intent.getStringExtra(MusicService.BROADCAST_ACTION)
            broadcastAction?.let {
                when (it) {
                    CherryBroadcastReceiver.ACTION_PLAY_OR_PAUSE -> {
                        if (isPlaying()) {
                            pause()
                        } else {
                            play()
                        }
                    }
                    CherryBroadcastReceiver.ACTION_PREVIOUS -> {
                        loadAnotherMusic(false)
                    }
                    CherryBroadcastReceiver.ACTION_NEXT -> {
                        loadAnotherMusic()
                    }
                }
            }

            // 设置播放源
            songs?.let {
                setSongs(it)
            }

            if (playIndex >= 0) {
                loadMediaByPosition(playIndex, isOnlyLoad)
            }
        }.start()
    }


    /**
     * 用于记录这次的播放的播放记录
     */
    override fun storeSongsAndIndex() {
        // 存储播放的index
        mContext.setSPValue({
            putInt(LAST_PLAY_INDEX, getCurrentPlayIndex())
        })
        // 存储当前播放列表
        getSongs()?.let { songs ->
            mSongsDatabase?.songDao()?.deleteAllSongs()
            mSongsDatabase?.songDao()?.insertSongs(songs)
            mContext.setSPValue(
                setValue = {
                    putBoolean(IS_HAVE_RECORD, true)
                }
            )
        }
    }

    override fun setSongs(songs: List<Song>) {
        mPlayerAdapter.setSongs(songs)
    }

    override fun getSongs(): List<Song>? = mPlayerAdapter.getSongs()

    override fun addPlaybackInfoListener(listener: PlaybackInfoListener) {
        mPlayerAdapter.addPlaybackInfoListener(listener)
    }

    override fun removePlaybackInfoListener(listener: PlaybackInfoListener) {
        mPlayerAdapter.removePlaybackInfoListener(listener)
    }

    override fun removeAllPlaybackInfoListener() {
        mPlayerAdapter.removeAllPlaybackInfoListener()
    }

    override fun loadAnotherMusic(isNext: Boolean) {
        mPlayerAdapter.loadAnotherMusic(isNext)
    }

    override fun loadMediaByPosition(position: Int, isOnlyLoad: Boolean) {
        mPlayerAdapter.loadMediaByPosition(position, isOnlyLoad)
    }

    override fun loadMedia(resourcePath: String, isOnlyLoad: Boolean, isOnline: Boolean) {
        mPlayerAdapter.loadMedia(resourcePath, isOnlyLoad, isOnline)
    }

    override fun play() {
        mPlayerAdapter.play()
    }

    override fun pause() {
        mPlayerAdapter.pause()
    }

    override fun reset() {
        mPlayerAdapter.reset()
    }

    override fun isPlaying(): Boolean = mPlayerAdapter.isPlaying() ?: false

    override fun seekTo(position: Int) {
        mPlayerAdapter.seekTo(position)
    }

    override fun release() {
        mPlayerAdapter.release()
    }

    override fun getCurrentPlayIndex(): Int = mPlayerAdapter.getCurrentPlayIndex()

}