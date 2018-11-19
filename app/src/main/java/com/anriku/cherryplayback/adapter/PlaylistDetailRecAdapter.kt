package com.anriku.cherryplayback.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.map.PlaylistDetailSongToSong
import com.anriku.cherryplayback.map.SigerDetailSongToSong
import com.anriku.cherryplayback.model.PlaylistDetail
import com.anriku.cherryplayback.model.SingerDetail
import com.anriku.cherryplayback.rxjava.ExecuteOnceObserver
import com.anriku.cherryplayback.service.MusicService
import com.anriku.cherryplayback.utils.extensions.errorHandler
import com.anriku.cherryplayback.utils.extensions.setSchedulers
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import java.lang.StringBuilder

/**
 * Created by anriku on 2018/11/18.
 */

class PlaylistDetailRecAdapter(context: Context, private val mPlaylistDetailSongs: List<PlaylistDetail.CdlistBean.SonglistBean>)
    : BaseRecAdapter(context) {
    override fun getThePositionLayoutId(position: Int): Int = R.layout.playlist_detail_rec_item


    override fun getItemCount(): Int = mPlaylistDetailSongs.size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemView = holder.itemView
        val song = mPlaylistDetailSongs[position]

        itemView.findViewById<TextView>(R.id.tv_index).apply {
            text = "${position + 1}"
        }

        itemView.findViewById<TextView>(R.id.tv_song_name).apply {
            text = song.songname
        }

        itemView.findViewById<TextView>(R.id.tv_artist).apply {
            val artistBuilder = StringBuilder()
            for ((index, singer) in song.singer.withIndex()) {
                artistBuilder.append(singer.name)
                if (index != song.singer.size - 1) {
                    artistBuilder.append("·")
                }
            }
            text = artistBuilder.toString()
        }

        itemView.setOnClickListener { _ ->

            Observable.create(ObservableOnSubscribe<List<PlaylistDetail.CdlistBean.SonglistBean>> {
                it.onNext(mPlaylistDetailSongs)
            }).setSchedulers()
                    .errorHandler()
                    .map(PlaylistDetailSongToSong())
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