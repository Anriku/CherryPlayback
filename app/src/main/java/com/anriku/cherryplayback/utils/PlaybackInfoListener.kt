package com.anriku.cherryplayback.utils

import androidx.annotation.IntDef

/**
 * Created by anriku on 2018/10/31.
 */

abstract class PlaybackInfoListener {

    companion object {
        const val INVALID = -1
        const val PLAY = 0
        const val PAUSE = 1
        const val RESET = 2
        const val COMPLETE = 3
    }

    @IntDef(value = [INVALID, PLAY, PAUSE, RESET, COMPLETE])
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class State

    public fun convertStateToString(@State state: Int) = when (state) {
        INVALID -> "invalid"
        PLAY -> "play"
        PAUSE -> "pause"
        RESET -> "reset"
        COMPLETE -> "complete"
        else -> "N/A"
    }

    abstract fun onDurationChanged(duration: Int)

    abstract fun onPositionChanged(position: Int)

    abstract fun onStateChange(@State state: Int)

    abstract fun onComplete()

}