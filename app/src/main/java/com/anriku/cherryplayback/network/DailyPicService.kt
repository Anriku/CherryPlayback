package com.anriku.cherryplayback.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by anriku on 2018/11/16.
 */

interface DailyPicService {

    /**
     * 获取必应的每日一图
     *
     */
    @GET("api/bing_pic")
    fun getDailyPic(): Call<ResponseBody>

}