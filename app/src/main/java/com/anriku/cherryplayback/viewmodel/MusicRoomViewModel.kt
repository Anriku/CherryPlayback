package com.anriku.cherryplayback.viewmodel

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.network.ApiGenerate
import com.anriku.cherryplayback.network.BASE_DAILY_PIC
import com.anriku.cherryplayback.network.DailyPicService
import com.anriku.cherryplayback.network.ErrorHandler
import com.anriku.cherryplayback.utils.extensions.simpleEnqueue
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by anriku on 2018/11/16.
 */

class MusicRoomViewModel : ViewModel() {

    private lateinit var mDailyPicService: DailyPicService

    fun getDailyPic(imageView: ImageView) {
        mDailyPicService = ApiGenerate.getApiService(DailyPicService::class.java, BASE_DAILY_PIC)

        // 获取必应每日一图
        mDailyPicService.getDailyPic().simpleEnqueue(object : ErrorHandler {
            override fun handle(e: Throwable?): Boolean {
                imageView.setImageResource(R.drawable.default_daily_pic)
                return true
            }

        }, onSimpleResponse = { _, response ->
            Glide.with(imageView)
                    .setDefaultRequestOptions(
                            RequestOptions().error(R.drawable.default_daily_pic)
                    )
                    .load(response.body()?.string())
                    .into(imageView)
        })
    }

}