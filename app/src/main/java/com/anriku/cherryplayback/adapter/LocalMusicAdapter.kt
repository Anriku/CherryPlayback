package com.anriku.cherryplayback.adapter

import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.service.MusicService

/**
 * Created by anriku on 2018/11/14.
 */

class LocalMusicAdapter(context: Context, private val mSongs: ArrayList<Song>) :
    BaseRecAdapter(context) {

    private var isLoadSongs: Boolean = false

    override fun getThePositionLayoutId(position: Int): Int = R.layout.music_list_rec_item

    override fun getItemCount(): Int = mSongs.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        holder.itemView.findViewById<TextView>(R.id.song).apply {
            text = mSongs[position].title.toString()
        }
        holder.itemView.findViewById<TextView>(R.id.artist).apply {
            text = mSongs[position].artist.toString()
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MusicService::class.java)
            if (!isLoadSongs) {
                intent.putExtra(MusicService.SONGS, mSongs)
                isLoadSongs = true
            }

            intent.putExtra(MusicService.IS_ONLY_LOAD, false)
            intent.putExtra(MusicService.PLAY_INDEX, position)
            ContextCompat.startForegroundService(context, intent)
        }
    }
}