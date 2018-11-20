package com.anriku.cherryplayback.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.anriku.cherryplayback.service.MusicService
import com.anriku.cherryplayback.utils.IMusicBinder

/**
 * Created by anriku on 2018/11/19.
 */

class CherryBroadcastReceiver : BroadcastReceiver() {


    companion object {
        const val TAG = "CherryBroadcastReceiver"

        const val ACTION_PLAY_OR_PAUSE = "com.anriku.cherryplayback.PLAY_OR_PAUSE"
        const val ACTION_PREVIOUS = "com.anriku.cherryplayback.PLAY_PREVIOUS"
        const val ACTION_NEXT = "com.anriku.cherryplayback.PLAY_NEXT"
    }

    override fun onReceive(context: Context, intent: Intent) {

        when (intent.action) {
            ACTION_PLAY_OR_PAUSE -> {
                val actionIntent = Intent(context, MusicService::class.java).apply {
                    putExtra(MusicService.BROADCAST_ACTION, ACTION_PLAY_OR_PAUSE)
                }
                context.startService(actionIntent)
            }
            ACTION_PREVIOUS -> {
                val actionIntent = Intent(context, MusicService::class.java).apply {
                    putExtra(MusicService.BROADCAST_ACTION, ACTION_PREVIOUS)
                }
                context.startService(actionIntent)
            }
            ACTION_NEXT -> {
                val actionIntent = Intent(context, MusicService::class.java).apply {
                    putExtra(MusicService.BROADCAST_ACTION, ACTION_NEXT)
                }
                context.startService(actionIntent)
            }
        }
    }
}