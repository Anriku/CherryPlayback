package com.anriku.cherryplayback.map

import com.anriku.cherryplayback.model.RankSong
import com.anriku.cherryplayback.model.Song
import io.reactivex.functions.Function
import java.lang.StringBuilder

/**
 * Created by anriku on 2018/11/30.
 */

class NewSongToSong: Function<List<RankSong.SonglistBean>, ArrayList<Song>> {
    
    override fun apply(onlineSongs: List<RankSong.SonglistBean>): ArrayList<Song> {
        val songs = ArrayList<Song>()

        for (onlineSong in onlineSongs) {
            if (onlineSong.data.songmid == null) {
                continue
            }
            val song = Song()
            song.musicType = Song.ONLINE
            song.albumId = onlineSong.data.albumid.toString()
            song.id = onlineSong.data.songid.toLong()
            song.title = onlineSong.data.songname
            song.mineType = "audio/mpeg"
            onlineSong.data.singer?.let {
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
            song.data = onlineSong.data.songmid

            songs.add(song)
        }
        return songs
    }
}