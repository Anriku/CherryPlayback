package com.anriku.cherryplayback.network

import com.anriku.cherryplayback.model.*
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by anriku on 2018/11/8.
 */

interface QQMusicService {

    /**
     * 获取歌手列表
     *
     * @param pageSize 每一页的大小。第一页的index是1
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
     * @param begin 从该歌手哪一首歌开始获取数据。第一个的index为0
     * @param num 每次获取多少首歌
     */
    @FormUrlEncoded
    @POST("v8/fcg-bin/fcg_v8_singer_track_cp.fcg")
    fun getSingerDetail(
        @Field("singermid") singerMid: String,
        @Field("num") num: Int,
        @Field("begin") begin: Int,

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
    ): Observable<SingerDetail>


    /**
     * 获取歌曲的vKey
     *
     * @param songMid 歌曲mid。可从歌单、专辑、歌手、排行榜接口中获取
     * @param filename C400 + [songMid] + .m4a
     */
    @GET("base/fcgi-bin/fcg_music_express_mobile3.fcg")
    fun getSongVKey(
        @Query("songmid") songMid: String,
        @Query("filename") filename: String,

        @Query("g_tk") gTk: String = "5381",
        @Query("loginUin") loginUin: String = "0",
        @Query("hostUin") hostUin: String = "0",
        @Query("format") format: String = "json",
        @Query("inCharset") inCharset: String = "utf-8",
        @Query("outCharset") outCharset: String = "utf-8",
        @Query("notice") notice: String = "0",
        @Query("platform") platform: String = "yqq",
        @Query("needNewCode") needNewCode: String = "0",
        @Query("cid") cid: String = "205361747",
        @Query("uin") uin: String = "0",
        @Query("guid") guid: String = GUID
    ): Observable<SongVKey>

    /**
     * 根据关键词查询内容。
     *
     * @param content 查询的内容
     * @param n 每页多少内容
     * @param p 查询哪一页，从1开始
     */
    @GET("soso/fcgi-bin/client_search_cp")
    fun search(
        @Query("w") content: String,
        @Query("n") n: Int,
        @Query("p") p: Int,

        @Query("ct") ct: String = "24",
        @Query("qqmusic_ver") qqmusicVer: String = "1298",
        @Query("new_json") newJson: String = "1",
        @Query("remoteplace") remotePlace: String = "txt.yqq.center",
        @Query("searchid") searchId: String = "37602803789127241",
        @Query("t") t: String = "0",
        @Query("aggr") aggr: String = "1",
        @Query("cr") cr: String = "1",
        @Query("catZhida") catZhida: String = "1",
        @Query("lossless") lossLess: String = "0",
        @Query("flag_qc") flagQc: String = "0",

        @Query("g_tk") gTk: String = "5381",
        @Query("loginUin") loginUin: String = "0",
        @Query("hostUin") hostUin: String = "0",
        @Query("format") format: String = "json",
        @Query("inCharset") inCharset: String = "utf-8",
        @Query("outCharset") outCharset: String = "utf-8",
        @Query("notice") notice: String = "0",
        @Query("platform") platform: String = "yqq",
        @Query("needNewCode") needNewCode: String = "0"
    ): Observable<SearchResult>


    /**
     * 轮播图的获取
     */
    @GET("musichall/fcgi-bin/fcg_yqqhomepagerecommend.fcg")
    fun getSlides(
        @Query("g_tk") gTk: String = "701075963",
        @Query("uin") uin: String = "0",
        @Query("format") format: String = "json",
        @Query("inCharset") inCharset: String = "utf-8",
        @Query("outCharset") outCharset: String = "utf-8",
        @Query("notice") notice: String = "0",
        @Query("platform") platform: String = "h5",
        @Query("needNewCode") needNewCode: String = "1"
    ): Observable<Slide>


    /**
     * 歌单详情
     *
     * @param disstId 歌单id
     */
    @Headers("referer:https://y.qq.com/n/yqq/playlist/3602407677.html")
    @GET("qzone/fcg-bin/fcg_ucc_getcdinfo_byids_cp.fcg")
    fun getPlayListDetail(
        @Query("disstid") disstId: String,

        @Query("type") type: String = "1",
        @Query("json") json: String = "1",
        @Query("utf8") utf8: String = "1",
        @Query("onlysong") onlySong: String = "0",
        @Query("format") format: String = "json",
        @Query("g_tk") gTk: String = "701075963",
        @Query("loginUin") loginUin: String = "0",
        @Query("hostUin") hostUin: String = "0",
        @Query("inCharset") inCharset: String = "utf-8",
        @Query("outCharset") outCharset: String = "utf-8",
        @Query("notice") notice: String = "0",
        @Query("platform") platform: String = "yqq",
        @Query("needNewCode") needNewCode: String = "0"
    ): Observable<PlaylistDetail>


    /**
     * 获取排行榜详情。
     *
     * @param topId 比如：26为热歌；27为新歌
     * @param songBegin 这个位置之后的歌曲
     * @param songNum 每页歌曲的数量
     *
     */
    @GET("v8/fcg-bin/fcg_v8_toplist_cp.fcg")
    fun getRankSong(
        @Query("topid") topId: String,
        @Query("song_begin") songBegin: Int,
        @Query("song_num") songNum: Int,

        @Query("format") format: String = "json",
        @Query("g_tk") gTk: String = "701075963",
        @Query("uin") uin: String = "0",
        @Query("inCharset") inCharset: String = "utf-8",
        @Query("outCharset") outCharset: String = "utf-8",
        @Query("platform") platform: String = "yqq",
        @Query("needNewCode") needNewCode: String = "0",
        @Query("tql") tql: String = "3",
        @Query("page") page: String = "1",
        @Query("type") type: String = "top"
    ): Observable<RankSong>


//    /**
//     * 获取歌词
//     *
//     * @param musicId 歌曲id。不是mid
//     */
//    @FormUrlEncoded
//    @POST("lyric/fcgi-bin/fcg_query_lyric.fcg")
//    fun getLyric(
//            @Field("musicid") musicId: String,
//
//            @Field("g_tk") gTk: String = "701075963",
//            @Field("uin") uin: String = "0",
//            @Field("format") format: String = "json",
//            @Field("inCharset") inCharset: String = "utf-8",
//            @Field("outCharset") outCharset: String = "utf-8",
//            @Field("notice") notice: String = "0",
//            @Field("platform") platform: String = "h5",
//            @Field("needNewCode") needNewCode: String = "1",
//            @Field("nobase64") nobase64: String = "1",
//            @Field("songtype") songType: String = "0"
//    ): Observable<Lyric>

}






















