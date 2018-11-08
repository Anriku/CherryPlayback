package com.anriku.cherryplayback.utils

import com.anriku.cherryplayback.model.SongVKey

/**
 * Created by anriku on 2018/11/8.
 */

object OnlineSongUtil {

    private const val BASE = "http://dl.stream.qqmusic.qq.com"

    fun constructUrl (songVKey: SongVKey): String {
        return "$BASE/${songVKey.data?.items?.get(0)?.filename}?vkey=${songVKey.data?.items?.get(0)?.vkey}" +
                "&guid=3655047200&fromtag=66"
    }

}