package com.anriku.cherryplayback.map

import com.anriku.cherryplayback.model.PlaylistDetail
import com.anriku.cherryplayback.model.SingerDetail
import com.anriku.cherryplayback.model.Song
import io.reactivex.functions.Function
import java.lang.StringBuilder

/**
 * Created by anriku on 2018/11/19.
 */

class PlaylistDetailSongToSong : Function<List<PlaylistDetail.CdlistBean.SonglistBean>, ArrayList<Song>> {


    override fun apply(onlineSongs: List<PlaylistDetail.CdlistBean.SonglistBean>): ArrayList<Song> {
        val songs = ArrayList<Song>()

        for (onlineSong in onlineSongs) {
            if (onlineSong.songmid == null) {
                continue
            }
            val song = Song()
            song.musicType = Song.ONLINE
            song.albumId = onlineSong.albumid.toString()
            song.id = onlineSong.songid.toLong()
            song.title = onlineSong.songname
            song.mineType = "audio/mpeg"
            onlineSong.singer?.let {
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
            song.data = onlineSong.songmid

            songs.add(song)
        }
        return songs
    }
}