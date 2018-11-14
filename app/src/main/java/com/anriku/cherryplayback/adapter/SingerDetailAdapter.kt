package com.anriku.cherryplayback.adapter

import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.extension.errorHandler
import com.anriku.cherryplayback.extension.setSchedulers
import com.anriku.cherryplayback.map.OnlineSongToSong
import com.anriku.cherryplayback.model.SingerDetail
import com.anriku.cherryplayback.rxjava.ExecuteOnceObserver
import com.anriku.cherryplayback.service.MusicService
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import java.lang.StringBuilder

/**
 * Created by anriku on 2018/11/9.
 */

class SingerDetailAdapter(private val mContext: Context) :
    BasePagedListAdapter<SingerDetail.DataBean.ListBean>(mContext, diffCallback) {

    companion object {
        private const val TAG = "SingerDetailAdapter"

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

    private var isLoadSongs: Boolean = false


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

        itemView.setOnClickListener { _ ->

            Observable.create(ObservableOnSubscribe<List<SingerDetail.DataBean.ListBean>> {
                it.onNext(currentList?.snapshot() ?: mutableListOf())
            }).setSchedulers(
                AndroidSchedulers.mainThread(),
                AndroidSchedulers.mainThread(),
                AndroidSchedulers.mainThread()
            )
                .errorHandler()
                .map(OnlineSongToSong())
                .subscribe(ExecuteOnceObserver(
                    onExecuteOnceNext = {
                        val intent = Intent(mContext, MusicService::class.java)
                        if (!isLoadSongs) {
                            intent.putExtra(MusicService.SONGS, it)
                        }

                        intent.putExtra(MusicService.IS_ONLINE, true)
                        intent.putExtra(MusicService.PLAY_INDEX, position)
                        ContextCompat.startForegroundService(mContext, intent)

//                        val intent = Intent(context, ControlActivity::class.java).apply {
//                            putExtra(ControlActivity.PLAY_INDEX, position)
//                            putParcelableArrayListExtra(ControlActivity.SONGS, it)
//                        }
//                        context.startActivity(intent)
                    }
                ))
        }
    }
}