package com.anriku.cherryplayback.network

import com.anriku.cherryplayback.model.Lyric
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by anriku on 2018/11/17.
 */

interface LyricService {


    /**
     * 获取歌词
     *
     * @param id 歌曲id
     * @param idMod100 歌曲id % 100.
     */
    @GET("miniportal/static/lyric/{idMod100}/{id}.xml")
    fun getLyric(
        @Path("idMod100") idMod100: Long,
        @Path("id") id: Long
    ): Observable<Lyric>
}