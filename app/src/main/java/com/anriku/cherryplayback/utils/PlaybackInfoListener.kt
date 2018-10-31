package com.anriku.cherryplayback.utils

import androidx.annotation.IntDef

/**
 * Created by anriku on 2018/10/31.
 */

abstract class PlaybackInfoListener {

    companion object {
        const val INVALID = -1
        const val PLAYING = 0
        const val PAUSED = 1
        const val RESET = 2
        const val COMPLETED = 3
    }

    @IntDef(value = [INVALID, PLAYING, PAUSED, RESET, COMPLETED])
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class State

    public fun convertStateToString(@State state: Int) = when (state) {
        INVALID -> "invalidate"
        PLAYING -> "playing"
        PAUSED -> "pause"
        RESET -> "reset"
        COMPLETED -> "completed"
        else -> "N/A"
    }

    abstract fun onDurationChanged(duration: Int)

    abstract fun onPositionChanged(position: Int)

    abstract fun onStateChange(@State state: Int)

    abstract fun onComplete()

}