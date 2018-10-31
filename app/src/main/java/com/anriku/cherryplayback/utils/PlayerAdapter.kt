package com.anriku.cherryplayback.utils

/**
 * Created by anriku on 2018/10/31.
 */

interface PlayerAdapter {

    fun loadMedia(resourcePath: String, isOnlineData: Boolean)

    fun play()

    fun pause()

    fun reset()

    fun isPlaying(): Boolean

    fun initializeProgressCallback()

    fun seekTo(position: Int)

    fun release()

}