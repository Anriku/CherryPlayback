package com.anriku.cherryplayback.ui.paing

import androidx.paging.PageKeyedDataSource
import com.anriku.cherryplayback.extension.errorHandler
import com.anriku.cherryplayback.extension.setSchedulers
import com.anriku.cherryplayback.model.SingerList
import com.anriku.cherryplayback.network.ApiGenerate
import com.anriku.cherryplayback.network.QQMusicService
import com.anriku.cherryplayback.rxjava.ExecuteOnceObserver
import com.anriku.cherryplayback.utils.LogUtil

/**
 * Created by anriku on 2018/11/8.
 */

class SingerListDataSource : PageKeyedDataSource<Int, SingerList.DataBean.ListBean>() {


    companion object {
       private const val TAG = "SingerListDataSource"
    }
    private lateinit var mQQMusicService: QQMusicService


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, SingerList.DataBean.ListBean>
    ) {
        mQQMusicService = ApiGenerate.getApiService(QQMusicService::class.java)
        mQQMusicService.getSingerList(params.requestedLoadSize, 1)
            .setSchedulers()
            .errorHandler()
            .subscribe(ExecuteOnceObserver(
                onExecuteOnceNext = {
                    it.data?.list ?: return@ExecuteOnceObserver

                    LogUtil.d(TAG, it.data!!.list!!.toString())
                    callback.onResult(it.data!!.list!!, 0, 2)
                }
            ))
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, SingerList.DataBean.ListBean>
    ) {
        mQQMusicService.getSingerList(params.requestedLoadSize, params.key)
            .setSchedulers()
            .errorHandler()
            .subscribe(ExecuteOnceObserver(
                onExecuteOnceNext = {
                    it.data?.list ?: return@ExecuteOnceObserver

                    callback.onResult(it.data!!.list!!, params.key + 1)
                }
            ))
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, SingerList.DataBean.ListBean>
    ) {
    }
}