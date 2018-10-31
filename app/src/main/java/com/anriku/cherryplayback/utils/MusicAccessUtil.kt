package com.anriku.cherryplayback.utils

import android.content.Context
import android.database.Cursor
import android.widget.Toast
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.model.Song

/**
 * Created by anriku on 2018/10/31.
 */

class MusicAccessUtil(private val mContext: Context) {

    private val mUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

    fun getMusics(): List<Song> {
        val songs = mutableListOf<Song>()
        val resolver = mContext.contentResolver
        val cursor: Cursor? = resolver.query(mUri, null, null, null, null)

        val id = android.provider.MediaStore.Audio.Media._ID
        val data = android.provider.MediaStore.Audio.Media.DATA
        val size = android.provider.MediaStore.Audio.Media.SIZE
        val displayName = android.provider.MediaStore.Audio.Media.DISPLAY_NAME
        val title = android.provider.MediaStore.Audio.Media.TITLE
        val dateAdded = android.provider.MediaStore.Audio.Media.DATE_ADDED
        val dateModified = android.provider.MediaStore.Audio.Media.DATE_MODIFIED
        val mineType = android.provider.MediaStore.Audio.Media.MIME_TYPE
        val duration = android.provider.MediaStore.Audio.Media.DURATION
        val artist = android.provider.MediaStore.Audio.Media.ARTIST
        val composer = android.provider.MediaStore.Audio.Media.COMPOSER
        val album = android.provider.MediaStore.Audio.Media.ALBUM
        val year = android.provider.MediaStore.Audio.Media.YEAR
        val isMusic = android.provider.MediaStore.Audio.Media.IS_MUSIC


        when {
            cursor == null -> {
                Toast.makeText(mContext, mContext.getString(R.string.get_music_failed), Toast.LENGTH_LONG).show()
            }
            !cursor.moveToFirst() -> {
                Toast.makeText(mContext, mContext.getString(R.string.none_music), Toast.LENGTH_LONG).show()
            }
            else -> {
                cursor.moveToFirst()

                do {
                    val song = Song()
                    song.isMusic = cursor.getInt(cursor.getColumnIndex(isMusic))
                    song.duration = cursor.getLong(cursor.getColumnIndex(duration))

                    // 略去不是音乐的或者时间小于一分钟的音频
                    if (song.isMusic != 0 || song.duration < 1000 * 60) {
                        continue
                    }

                    song.id = cursor.getLong(cursor.getColumnIndex(id))
                    song.data = cursor.getString(cursor.getColumnIndex(data))
                    song.size = cursor.getLong(cursor.getColumnIndex(size))
                    song.displayName = cursor.getString(cursor.getColumnIndex(displayName))
                    song.title = cursor.getString(cursor.getColumnIndex(title))
                    song.dateAdded = cursor.getLong(cursor.getColumnIndex(dateAdded))
                    song.dateModified = cursor.getLong(cursor.getColumnIndex(dateModified))
                    song.mineType = cursor.getString(cursor.getColumnIndex(mineType))
                    song.artist = cursor.getString(cursor.getColumnIndex(artist))
                    song.composer = cursor.getString(cursor.getColumnIndex(composer))
                    song.album = cursor.getString(cursor.getColumnIndex(album))
                    song.year = cursor.getInt(cursor.getColumnIndex(year))

                    songs.add(song)
                } while (cursor.moveToNext())
            }
        }

        cursor?.close()
        return songs
    }

}