package com.anriku.cherryplayback.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.anriku.cherryplayback.model.SingerDetail
import com.anriku.cherryplayback.ui.paing.SingerDetailDataSource

/**
 * Created by anriku on 2018/11/9.
 */

class SingerDetailViewModel(private val mSingerMid: String) : ViewModel() {

    val singerDetail = LivePagedListBuilder(
        object : DataSource.Factory<Int, SingerDetail.DataBean.ListBean>() {
            override fun create(): DataSource<Int, SingerDetail.DataBean.ListBean> =
                SingerDetailDataSource(mSingerMid)
        }, PagedList.Config.Builder()
            .setPageSize(50)
            .setEnablePlaceholders(true).build()
    ).build()


    @Suppress("UNCHECKED_CAST")
    class Factory(private val mSingerMid: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SingerDetailViewModel(mSingerMid) as T
        }
    }
}