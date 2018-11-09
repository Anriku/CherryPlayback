package com.anriku.cherryplayback.ui.paing

import androidx.paging.PageKeyedDataSource
import com.anriku.cherryplayback.extension.errorHandler
import com.anriku.cherryplayback.extension.setSchedulers
import com.anriku.cherryplayback.model.SingerDetail
import com.anriku.cherryplayback.network.ApiGenerate
import com.anriku.cherryplayback.network.QQMusicService
import com.anriku.cherryplayback.rxjava.ExecuteOnceObserver

/**
 * Created by anriku on 2018/11/9.
 */

class SingerDetailDataSource(private val mSingerMid: String) :
    PageKeyedDataSource<Int, SingerDetail.DataBean.ListBean>() {

    private lateinit var mQQMusicService: QQMusicService

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, SingerDetail.DataBean.ListBean>
    ) {
        mQQMusicService = ApiGenerate.getApiService(QQMusicService::class.java)
        mQQMusicService.getSingerDetail(mSingerMid, params.requestedLoadSize, 0)
            .setSchedulers()
            .errorHandler()
            .subscribe(ExecuteOnceObserver(
                onExecuteOnceNext = {
                    it.data?.list ?: return@ExecuteOnceObserver

                    callback.onResult(it.data!!.list!!, 0, 2)
                }
            ))
    }

    override fun loadAfter(
        params: LoadParams<Int>, callback: LoadCallback<Int, SingerDetail.DataBean.ListBean>
    ) {
        mQQMusicService.getSingerDetail(mSingerMid, params.requestedLoadSize, params.key)
            .setSchedulers()
            .errorHandler()
            .subscribe(ExecuteOnceObserver(
                onExecuteOnceNext = {
                    it.data?.list ?: return@ExecuteOnceObserver

                    callback.onResult(it.data!!.list!!, params.key + params.requestedLoadSize)
                }
            ))
    }

    override fun loadBefore(
        params: LoadParams<Int>, callback: LoadCallback<Int, SingerDetail.DataBean.ListBean>
    ) {
    }
}