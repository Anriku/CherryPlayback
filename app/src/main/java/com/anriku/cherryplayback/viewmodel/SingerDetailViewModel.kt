package com.anriku.cherryplayback.viewmodel

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.utils.extensions.errorHandler
import com.anriku.cherryplayback.utils.extensions.setSchedulers
import com.anriku.cherryplayback.model.SingerDetail
import com.anriku.cherryplayback.model.SingerList
import com.anriku.cherryplayback.network.*
import com.anriku.cherryplayback.rxjava.ExecuteOnceObserver
import com.anriku.cherryplayback.ui.paing.SingerDetailDataSource
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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

    fun setSingerImage(imageView: ImageView, singerInfo: SingerList.DataBean.ListBean) {

        singerInfo.fsinger_name.let {
            val enBracket = it.indexOf('(')
            val zhBracket = it.indexOf('（')
            val name = when {
                enBracket != -1 -> it.substring(0, enBracket)
                zhBracket != -1 -> it.substring(0, zhBracket)
                else -> it
            }

            Glide.with(imageView.context)
                .load(ImageUrl.getSingerImageUrl(singerInfo.fsinger_id.toLong()))
                .apply(RequestOptions().placeholder(R.drawable.ic_singer).error(R.drawable.ic_error))
                .into(imageView)
        }
    }
}