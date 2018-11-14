package com.anriku.cherryplayback.map

import com.anriku.cherryplayback.model.SingerDetail
import com.anriku.cherryplayback.model.Song
import io.reactivex.functions.Function
import java.lang.StringBuilder

/**
 * Created by anriku on 2018/11/13.
 */

class OnlineSongToSong : Function<List<SingerDetail.DataBean.ListBean>, ArrayList<Song>> {
    override fun apply(onlineSongs: List<SingerDetail.DataBean.ListBean>): ArrayList<Song> {
        val songs = ArrayList<Song>()

        for (onlineSong in onlineSongs) {
            if (onlineSong.musicData?.songmid == null) {
                continue
            }
            val song = Song()
            song.id = onlineSong.musicData?.songid?.toLong() ?: System.currentTimeMillis()
            song.title = onlineSong.musicData?.songname
            song.mineType = "audio/mpeg"
            onlineSong.musicData?.singer?.let {
                val singersSb = StringBuilder()
                for ((index, singer) in it.withIndex()) {
                    singersSb.append(singer.name)
                    if (index != it.size - 1) {
                        singersSb.append('·')
                    }
                }
                song.artist = singersSb.toString()
            }
            song.whetherMusic = 1
            song.data = onlineSong.musicData?.songmid

            songs.add(song)
        }
        return songs
    }

}