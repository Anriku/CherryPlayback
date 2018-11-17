package com.anriku.cherryplayback.utils

import android.content.Context
import android.content.Intent
import android.os.Binder
import com.anriku.cherryplayback.adapter.PlayerAdapter
import com.anriku.cherryplayback.model.Song

/**
 * Created by anriku on 2018/11/2.
 */

abstract class IMusicBinder : Binder(), PlayerAdapter {

    companion object {
        const val SEQUENCE_PLAY = 0
        const val RANDOM_PLAY = 1
        const val SINGLE_PLAY = 2
    }

    abstract fun playSet(intent: Intent)

    abstract fun storeSongsAndIndex()
}