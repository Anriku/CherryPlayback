package com.anriku.cherryplayback.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.map.NewSongToSong
import com.anriku.cherryplayback.model.RankSong
import com.anriku.cherryplayback.rxjava.ExecuteOnceObserver
import com.anriku.cherryplayback.service.MusicService
import com.anriku.cherryplayback.utils.TextViewUtil
import com.anriku.cherryplayback.utils.extensions.errorHandler
import com.anriku.cherryplayback.utils.extensions.setSchedulers
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import java.lang.StringBuilder

/**
 * Created by anriku on 2018/11/30.
 */

class RankSongRecAdapter(context: Context) :
    BasePagedListAdapter<RankSong.SonglistBean>(context, diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<RankSong.SonglistBean>() {
            override fun areItemsTheSame(
                oldItem: RankSong.SonglistBean,
                newItem: RankSong.SonglistBean
            ): Boolean {
                return oldItem.vid == newItem.vid
            }

            override fun areContentsTheSame(
                oldItem: RankSong.SonglistBean,
                newItem: RankSong.SonglistBean
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    private val mSongStatus: List<Drawable?> by lazy(LazyThreadSafetyMode.NONE) {
        listOf(
            ContextCompat.getDrawable(context, R.drawable.ic_up)?.apply {
                setBounds(0, 0, 40, 40)
            },
            ContextCompat.getDrawable(context, R.drawable.ic_down)?.apply {
                setBounds(0, 0, 40, 40)
            },
            ContextCompat.getDrawable(context, R.drawable.ic_keep)?.apply {
                setBounds(0, 0, 40, 40)
            }
        )
    }

    override fun getThePositionLayoutId(position: Int) =
        R.layout.rank_song_rec_item

    override fun onBindViewHolder(holder: BasePagedListAdapter.BaseViewHolder, position: Int) {
        val itemView = holder.itemView
        val newSong = getItem(position)
        newSong ?: return

        val curCount = newSong.cur_count.toInt()
        val oldCount = newSong.old_count.toInt()

        itemView.findViewById<TextView>(R.id.tv_rank).apply {
            text = curCount.toString()
        }

        val tvRankStatus = itemView.findViewById<TextView>(R.id.tv_rank_status)

        when {
            oldCount == 0 -> {
                TextViewUtil.setTextView(tvRankStatus, null, context.resources.getString(R.string.new_song))
            }
            curCount > oldCount -> {
                TextViewUtil.setTextView(tvRankStatus, mSongStatus[0], (curCount - oldCount).toString())
            }
            oldCount > curCount -> {
                TextViewUtil.setTextView(tvRankStatus, mSongStatus[1], (oldCount - curCount).toString())
            }
            else -> {
                TextViewUtil.setTextView(tvRankStatus, mSongStatus[2])
            }
        }

        itemView.findViewById<TextView>(R.id.tv_song_name).apply {
            text = newSong.data.songname
        }

        itemView.findViewById<TextView>(R.id.tv_artist).apply {
            val artistBuilder = StringBuilder()
            for ((index, singer) in newSong.data.singer.withIndex()) {
                artistBuilder.append(singer.name)
                if (index != newSong.data.singer.size - 1) {
                    artistBuilder.append("·")
                }
            }
            text = artistBuilder.toString()
        }

        itemView.setOnClickListener { _ ->
            Observable.create(ObservableOnSubscribe<List<RankSong.SonglistBean>> {
                it.onNext(currentList?.snapshot() ?: mutableListOf())
            }).setSchedulers()
                .errorHandler()
                .map(NewSongToSong())
                .subscribe(ExecuteOnceObserver(
                    onExecuteOnceNext = {
                        val intent = Intent(context, MusicService::class.java)
                        intent.putExtra(MusicService.SONGS, it)

                        intent.putExtra(MusicService.IS_ONLY_LOAD, false)
                        intent.putExtra(MusicService.PLAY_INDEX, position)
                        ContextCompat.startForegroundService(context, intent)
                    }
                ))
        }
    }
}