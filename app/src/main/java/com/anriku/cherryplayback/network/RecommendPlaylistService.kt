package com.anriku.cherryplayback.network

import com.anriku.cherryplayback.model.RecommendPlaylist
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by anriku on 2018/11/18.
 */

interface RecommendPlaylistService {

    /**
     * 推荐歌单的获取
     */
    @GET("cgi-bin/musicu.fcg")
    fun getPlayList(
            @Query("g_tk") gTk: String = "701075963",
            @Query("format") format: String = "json",
            @Query("inCharset") inCharset: String = "utf-8",
            @Query("outCharset") outCharset: String = "utf-8",
            @Query("platform") platform: String = "yqq",
            @Query("needNewCode") needNewCode: String = "1",
            @Query("data") data: String = """{
    "comm": {
        "ct": 24
    },
    "recomPlaylist": {
        "method": "get_hot_recommend",
        "param": {
            "async": 1,
            "cmd": 2
        },
        "module": "playlist.HotRecommendServer"
    }
}"""
    ): Observable<RecommendPlaylist>
}