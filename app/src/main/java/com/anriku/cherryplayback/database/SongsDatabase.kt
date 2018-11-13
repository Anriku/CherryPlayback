package com.anriku.cherryplayback.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anriku.cherryplayback.model.Song

/**
 * Created by anriku on 2018/11/13.
 */

@Database(entities = [Song::class], version = 1)
abstract class SongsDatabase : RoomDatabase() {

    abstract fun songDao(): SongDao

    companion object {
        private var INSTANCE: SongsDatabase? = null

        fun getDatabase(context: Context): SongsDatabase? {
            if (INSTANCE == null) {
                synchronized(SongsDatabase::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            SongsDatabase::class.java, "songs_database"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}