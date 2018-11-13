package com.anriku.cherryplayback.database

import android.database.Observable
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anriku.cherryplayback.model.Song

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
    fun getAllSongs(): Observable<List<Song>>

    @Query("DELETE FROM songs")
    fun deleteAllSongs()
}