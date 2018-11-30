package com.anriku.cherryplayback.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.anriku.cherryplayback.config.HOT_SONG
import com.anriku.cherryplayback.config.NEW_SONG
import com.anriku.cherryplayback.model.RankSong
import com.anriku.cherryplayback.ui.paing.RankSongDataSource

/**
 * Created by anriku on 2018/11/30.
 */

class RankSongViewModel(private val mTopId: String) : ViewModel() {

    val rankSong = LivePagedListBuilder(
        object : DataSource.Factory<Int, RankSong.SonglistBean>() {
            override fun create(): DataSource<Int, RankSong.SonglistBean> =
                RankSongDataSource(mTopId)
        }, PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(true).build()
    ).build()

    @Suppress("UNCHECKED_CAST")
    class Factory(private val mTopId: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RankSongViewModel(mTopId) as T
        }
    }

}