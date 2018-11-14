package com.anriku.cherryplayback.service

import android.content.Context
import com.anriku.cherryplayback.adapter.CherryMusicPlayer
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.utils.IMusicBinder
import com.anriku.cherryplayback.utils.PlaybackInfoListener
import com.anriku.cherryplayback.adapter.PlayerAdapter

/**
 * Created by anriku on 2018/11/2.
 */

/**
 * 用于与Activity或者Notification通信的Binder.并且还充当这Activity操作[PlayerAdapter]的中介
 */
class MusicBinder(private val mContext: Context) : IMusicBinder() {

    private val mPlayerAdapter: PlayerAdapter by lazy(LazyThreadSafetyMode.NONE) {
        CherryMusicPlayer(mContext)
    }

    override fun setSongs(
        songs: List<Song>,
        isOnlineMusic: Boolean
    ) {
        mPlayerAdapter.setSongs(songs, isOnlineMusic)
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

    override fun loadMedia(resourcePath: String, isOnlyLoad: Boolean) {
        mPlayerAdapter.loadMedia(resourcePath, isOnlyLoad)
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

    override fun getCurrentPlayIndex(): Int = mPlayerAdapter.getCurrentPlayIndex() ?: -1
}