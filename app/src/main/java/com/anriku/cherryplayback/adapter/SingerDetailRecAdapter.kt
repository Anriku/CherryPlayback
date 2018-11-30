package com.anriku.cherryplayback.adapter

import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.utils.extensions.errorHandler
import com.anriku.cherryplayback.utils.extensions.setSchedulers
import com.anriku.cherryplayback.map.SigerDetailSongToSong
import com.anriku.cherryplayback.model.SingerDetail
import com.anriku.cherryplayback.rxjava.ExecuteOnceObserver
import com.anriku.cherryplayback.service.MusicService
import com.anriku.cherryplayback.utils.SafeOnclickListener
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import java.lang.StringBuilder

/**
 * Created by anriku on 2018/11/9.
 */

class SingerDetailRecAdapter(private val mContext: Context) :
    BasePagedListAdapter<SingerDetail.DataBean.ListBean>(mContext, diffCallback) {

    companion object {
        private const val TAG = "SingerDetailRecAdapter"

        val diffCallback = object : DiffUtil.ItemCallback<SingerDetail.DataBean.ListBean>() {
            override fun areItemsTheSame(
                oldItem: SingerDetail.DataBean.ListBean,
                newItem: SingerDetail.DataBean.ListBean
            ): Boolean {
                return oldItem.vid == newItem.vid
            }

            override fun areContentsTheSame(
                oldItem: SingerDetail.DataBean.ListBean,
                newItem: SingerDetail.DataBean.ListBean
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getThePositionLayoutId(position: Int): Int =
        R.layout.singer_detail_rec_item

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemView = holder.itemView
        val item = getItem(position)

        itemView.findViewById<TextView>(R.id.tv_song).apply {
            text = item?.musicData?.songname
        }

        itemView.findViewById<TextView>(R.id.tv_artist).apply {
            val singers = item?.musicData?.singer ?: return

            val artistBuilder = StringBuilder()
            for ((index, singer) in singers.withIndex()) {
                artistBuilder.append(singer.name)
                if (index != singers.size - 1) {
                    artistBuilder.append("·")
                }
            }

            text = artistBuilder.toString()
        }

        itemView.setOnClickListener(SafeOnclickListener { _ ->
            Observable.create(ObservableOnSubscribe<List<SingerDetail.DataBean.ListBean>> {
                it.onNext(currentList?.snapshot() ?: mutableListOf())
            }).setSchedulers()
                .errorHandler()
                .map(SigerDetailSongToSong())
                .subscribe(ExecuteOnceObserver(
                    onExecuteOnceNext = {
                        val intent = Intent(mContext, MusicService::class.java)
                        intent.putExtra(MusicService.SONGS, it)

                        intent.putExtra(MusicService.IS_ONLY_LOAD, false)
                        intent.putExtra(MusicService.PLAY_INDEX, position)
                        ContextCompat.startForegroundService(mContext, intent)
                    }
                ))
        })
    }
}