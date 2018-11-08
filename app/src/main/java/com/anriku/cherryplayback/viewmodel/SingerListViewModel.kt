package com.anriku.cherryplayback.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.anriku.cherryplayback.model.SingerList
import com.anriku.cherryplayback.ui.paing.SingerListDataSource

/**
 * Created by anriku on 2018/11/8.
 */

class SingerListViewModel : ViewModel() {

    val singerList = LivePagedListBuilder(
        object : DataSource.Factory<Int, SingerList.DataBean.ListBean>() {
            override fun create(): DataSource<Int, SingerList.DataBean.ListBean> = SingerListDataSource()
        }, PagedList.Config.Builder()
            .setPageSize(100)
            .setEnablePlaceholders(true).build()
    ).build()



}