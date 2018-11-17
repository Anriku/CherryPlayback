package com.anriku.cherryplayback.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anriku.cherryplayback.model.Slide
import com.anriku.cherryplayback.network.ApiGenerate
import com.anriku.cherryplayback.network.QQMusicService
import com.anriku.cherryplayback.rxjava.ExecuteOnceObserver
import com.anriku.cherryplayback.utils.extensions.errorHandler
import com.anriku.cherryplayback.utils.extensions.setSchedulers

/**
 * Created by anriku on 2018/11/16.
 */

class SquareViewModel: ViewModel() {

    val slides: MutableLiveData<List<Slide.DataBean.SliderBean>> by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<List<Slide.DataBean.SliderBean>>().apply {
            value = mutableListOf()
        }
    }
    private val mMusicService: QQMusicService by lazy {
        ApiGenerate.getApiService(QQMusicService::class.java)
    }

    fun getSlides() {
        mMusicService.getSlides()
            .setSchedulers()
            .errorHandler()
            .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                it.data?.slider?.let { notNullSlides ->
                    slides.value = notNullSlides
                }
            }))
    }

}