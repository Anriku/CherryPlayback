package com.anriku.cherryplayback.service

import android.content.Context
import com.anriku.cherryplayback.adapter.CherryPlayer
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.utils.IMusicBinder
import com.anriku.cherryplayback.utils.PlaybackInfoListener
import com.anriku.cherryplayback.utils.PlayerAdapter

/**
 * Created by anriku on 2018/11/2.
 */

/**
 * 用于与Activity或者Notification通信的Binder.并且还充当这Activity操作[PlayerAdapter]的中介
 */
class MusicBinder(private val mContext: Context) : IMusicBinder() {

    private val mPlayerAdapter: PlayerAdapter by lazy(LazyThreadSafetyMode.NONE) {
        CherryPlayer(mContext)
    }

    override fun setSongs(songs: List<Song>) {
        mPlayerAdapter.setSongs(songs)
    }

    override fun getSongs(): List<Song> = mPlayerAdapter.getSongs()

    override fun addPlaybackInfoListener(listener: PlaybackInfoListener) {
        mPlayerAdapter.addPlaybackInfoListener(listener)
    }

    override fun removePlaybackInfoListener(listener: PlaybackInfoListener) {
        mPlayerAdapter.removePlaybackInfoListener(listener)
    }

    override fun removeAllPlaybackInfoListener() {
        mPlayerAdapter.removeAllPlaybackInfoListener()
    }

    override fun loadAnotherMusic(pattern: Int, isNext: Boolean) {
        mPlayerAdapter.loadAnotherMusic(pattern, isNext)
    }

    override fun loadMediaByPosition(position: Int) {
        mPlayerAdapter.loadMediaByPosition(position)
    }

    override fun loadMedia(resourcePath: String, isOnlineData: Boolean) {
        mPlayerAdapter.loadMedia(resourcePath, isOnlineData)
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

    override fun isPlaying(): Boolean = mPlayerAdapter.isPlaying()

    override fun seekTo(position: Int) {
        mPlayerAdapter.seekTo(position)
    }

    override fun release() {
        mPlayerAdapter.release()
    }

    override fun getCurrentPlayIndex(): Int = mPlayerAdapter.getCurrentPlayIndex()
}