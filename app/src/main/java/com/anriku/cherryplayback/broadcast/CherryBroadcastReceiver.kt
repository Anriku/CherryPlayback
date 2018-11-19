package com.anriku.cherryplayback.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.anriku.cherryplayback.utils.IMusicBinder

/**
 * Created by anriku on 2018/11/19.
 */

class CherryBroadcastReceiver() : BroadcastReceiver() {

    private lateinit var mMusicBinder: IMusicBinder

    
    companion object {
        const val TAG = "CherryBroadcastReceiver"

        const val MUSIC_BINDER = "music_binder"
        const val ACTION_PLAY_OR_PAUSE = "com.anriku.cherryplayback.PLAY_OR_PAUSE"
        const val ACTION_PREVIOUS = "com.anriku.cherryplayback.PLAY_PREVIOUS"
        const val ACTION_NEXT = "com.anriku.cherryplayback.PLAY_NEXT"
    }

    override fun onReceive(context: Context, intent: Intent) {

        when (intent.action) {
            ACTION_PLAY_OR_PAUSE -> {
                if (mMusicBinder.isPlaying()) {
                    mMusicBinder.pause()
                } else {
                    mMusicBinder.play()
                }
                Log.d(TAG, "play_and_pause")
            }
            ACTION_PREVIOUS -> {
                mMusicBinder.loadAnotherMusic(false)
            }
            ACTION_NEXT -> {
                mMusicBinder.loadAnotherMusic()
            }
        }
    }
}