package com.anriku.cherryplayback.viewmodel

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.config.HOT_SONG
import com.anriku.cherryplayback.config.NEW_SONG
import com.anriku.cherryplayback.model.RankSong
import com.anriku.cherryplayback.model.SingerDetail
import com.anriku.cherryplayback.network.*
import com.anriku.cherryplayback.ui.paing.RankSongDataSource
import com.anriku.cherryplayback.ui.paing.SingerDetailDataSource
import com.anriku.cherryplayback.utils.extensions.simpleEnqueue
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

/**
 * Created by anriku on 2018/11/16.
 */

class MusicRoomViewModel : ViewModel() {

    private val mDailyPicService: DailyPicService by lazy(LazyThreadSafetyMode.NONE) {
        ApiGenerate.getGsonApiService(DailyPicService::class.java, BASE_DAILY_PIC)
    }

    fun getDailyPic(imageView: ImageView) {

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
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        })
    }

}