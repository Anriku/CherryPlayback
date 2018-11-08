package com.anriku.cherryplayback.network

import com.anriku.cherryplayback.model.SingerDetail
import com.anriku.cherryplayback.model.SingerList
import com.anriku.cherryplayback.model.SingerWrapper
import com.anriku.cherryplayback.model.SongVKey
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by anriku on 2018/11/8.
 */

interface QQMusicService {

    /**
     * 获取歌手列表
     *
     * @param pageSize 每一页的大小
     * @param pageNum 查询那页
     */
    @FormUrlEncoded
    @POST("v8/fcg-bin/v8.fcg")
    fun getSingerList(
        @Field("pagesize") pageSize: Int,
        @Field("pagenum") pageNum: Int,

        @Field("channel") channel: String = "singer",
        @Field("page") page: String = "list",
        @Field("key") key: String = "all_all_all",
        @Field("g_tk") gTk: String = "5381",
        @Field("loginUin") loginUin: String = "0",
        @Field("hostUin") hostUin: String = "0",
        @Field("format") format: String = "json",
        @Field("inCharset") inCharset: String = "utf-8",
        @Field("outCharset") outCharset: String = "utf-8",
        @Field("notice") notice: String = "0",
        @Field("platform") platform: String = "yqq",
        @Field("needNewCode") needNewCode: String = "0"
    ): Observable<SingerList>


    /**
     * 查看歌手详情
     *
     * @param singerMid 歌手mid。可从歌手列表里面获取
     * @param begin 从该歌手哪一首歌开始获取数据
     * @param num 每次获取多少首歌
     */
    @GET("/v8/fcg-bin/fcg_v8_singer_track_cp.fcg")
    fun getSingerDetail(
        @Field("singermid") singerMid: String,
        @Field("begin") begin: String,
        @Field("num") num: String,

        @Field("order") order: String = "order",
        @Field("songstatus") songStatus: String = "1",
        @Field("g_tk") gTk: String = "5381",
        @Field("loginUin") loginUin: String = "0",
        @Field("hostUin") hostUin: String = "0",
        @Field("format") format: String = "json",
        @Field("inCharset") inCharset: String = "utf-8",
        @Field("outCharset") outCharset: String = "utf-8",
        @Field("notice") notice: String = "0",
        @Field("platform") platform: String = "yqq",
        @Field("needNewCode") needNewCode: String = "0"
    ): Observable<SingerWrapper<SingerDetail>>


    /**
     * 获取歌曲的vKey
     *
     * @param songMid 歌曲mid。可从歌单、专辑、歌手、排行榜接口中获取
     * @param filename C400 + [songMid] + .m4a
     */
    @GET("/base/fcgi-bin/fcg_music_express_mobile3.fcg")
    fun getSongVKey(
        @Field("songmid") songMid: String,
        @Field("filename") filename: String,

        @Field("g_tk") gTk: String = "5381",
        @Field("loginUin") loginUin: String = "0",
        @Field("hostUin") hostUin: String = "0",
        @Field("format") format: String = "json",
        @Field("inCharset") inCharset: String = "utf-8",
        @Field("outCharset") outCharset: String = "utf-8",
        @Field("notice") notice: String = "0",
        @Field("platform") platform: String = "yqq",
        @Field("needNewCode") needNewCode: String = "0",
        @Field("cid") cid: String = "205361747",
        @Field("uin") uin: String = "0",
        @Field("guid") guid: String = GUID
    ): Observable<SongVKey>
}