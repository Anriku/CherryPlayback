package com.anriku.cherryplayback.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anriku.cherryplayback.model.Song
import io.reactivex.Flowable

/**
 * Created by anriku on 2018/11/13.
 */

@Dao
interface SongDao {

    @Insert
    fun insertSong(song: Song)

    @Insert
    fun insertSongs(songs: List<Song>)

    @Query("SELECT * FROM songs")
    fun getAllSongs(): Flowable<List<Song>>

    @Query("DELETE FROM songs")
    fun deleteAllSongs()
}