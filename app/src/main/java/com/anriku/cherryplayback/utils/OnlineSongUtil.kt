package com.anriku.cherryplayback.utils

import com.anriku.cherryplayback.model.SongVKey

/**
 * Created by anriku on 2018/11/8.
 */

object OnlineSongUtil {

    private const val BASE = "http://dl.stream.qqmusic.qq.com"

    /**
     * 合成在线歌曲的Url。如果无法合成就返回null
     */
    fun constructUrl (songMid: String): String = "$BASE/C100$songMid.m4a"

}