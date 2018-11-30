package com.anriku.cherryplayback.ui.paing

import androidx.paging.PageKeyedDataSource
import com.anriku.cherryplayback.model.RankSong
import com.anriku.cherryplayback.network.ApiGenerate
import com.anriku.cherryplayback.network.QQMusicService
import com.anriku.cherryplayback.rxjava.ExecuteOnceObserver
import com.anriku.cherryplayback.utils.extensions.errorHandler
import com.anriku.cherryplayback.utils.extensions.setSchedulers

/**
 * Created by anriku on 2018/11/30.
 */
class RankSongDataSource(private val mTopId: String) : PageKeyedDataSource<Int, RankSong.SonglistBean>() {

    private val mQQMusicService: QQMusicService by lazy(LazyThreadSafetyMode.NONE) {
        ApiGenerate.getGsonApiService(QQMusicService::class.java)
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, RankSong.SonglistBean>
    ) {
        mQQMusicService
            .getRankSong(mTopId, 0, params.requestedLoadSize)
            .setSchedulers()
            .errorHandler()
            .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                callback.onResult(it.songlist, 0, params.requestedLoadSize)
            }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RankSong.SonglistBean>) {
        mQQMusicService
            .getRankSong(mTopId, params.key, params.requestedLoadSize)
            .setSchedulers()
            .errorHandler()
            .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                callback.onResult(it.songlist, params.key + params.requestedLoadSize)
            }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RankSong.SonglistBean>) {

    }
}