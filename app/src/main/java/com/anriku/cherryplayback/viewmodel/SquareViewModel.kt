package com.anriku.cherryplayback.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anriku.cherryplayback.model.PlaylistDetail
import com.anriku.cherryplayback.model.RecommendPlaylist
import com.anriku.cherryplayback.model.Slide
import com.anriku.cherryplayback.network.ApiGenerate
import com.anriku.cherryplayback.network.BASE_RECOMMEND_PLAYLIST
import com.anriku.cherryplayback.network.QQMusicService
import com.anriku.cherryplayback.network.RecommendPlaylistService
import com.anriku.cherryplayback.rxjava.ExecuteOnceObserver
import com.anriku.cherryplayback.utils.extensions.errorHandler
import com.anriku.cherryplayback.utils.extensions.setSchedulers

/**
 * Created by anriku on 2018/11/16.
 */

class SquareViewModel : ViewModel() {

    val slides: MutableLiveData<List<Slide.DataBean.SliderBean>> by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<List<Slide.DataBean.SliderBean>>().apply {
            value = mutableListOf()
        }
    }
    private val mQQMusicService: QQMusicService by lazy {
        ApiGenerate.getGsonApiService(QQMusicService::class.java)
    }
    private val mRecommendPlaylistService: RecommendPlaylistService by lazy(LazyThreadSafetyMode.NONE) {
        ApiGenerate.getGsonApiService(RecommendPlaylistService::class.java, BASE_RECOMMEND_PLAYLIST)
    }

    /**
     * 获取轮播图
     */
    fun getSlides() {
        mQQMusicService.getSlides()
                .setSchedulers()
                .errorHandler()
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                    it.data?.slider?.let { notNullSlides ->
                        slides.value = notNullSlides
                    }
                }))
    }


    /**
     * 获取推荐歌单
     */
    fun getRecommendPlayList(onGetRecommendPlaylist: (RecommendPlaylist) -> Unit) {
        mRecommendPlaylistService
                .getPlayList()
                .setSchedulers()
                .errorHandler()
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                    onGetRecommendPlaylist(it)
                }))
    }

    /**
     * 获取歌单详情
     */
    fun getPlaylistDetail(disstId: String, onGetPlaylistDetail: (PlaylistDetail) -> Unit) {
        mQQMusicService.getPlayListDetail(disstId)
                .setSchedulers()
                .errorHandler()
                .subscribe(ExecuteOnceObserver(
                        onExecuteOnceNext = {
                            onGetPlaylistDetail(it)
                        }
                ))
    }
}