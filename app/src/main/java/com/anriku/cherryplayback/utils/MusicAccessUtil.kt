package com.anriku.cherryplayback.utils

import android.Manifest
import android.database.Cursor
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.rxjava.ExecuteOnceObserver
import com.tbruyelle.rxpermissions2.RxPermissions

/**
 * 此类用于获取本地的音乐
 *
 * Created by anriku on 2018/10/31.
 */
class MusicAccessUtil(private val mActivity: FragmentActivity) {

    companion object {
        private const val TAG = "MusicAccessUtil"
    }

    private val mUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
    private var mRxPermission: RxPermissions? = null
    private var mSongs: List<Song>? = null

    /**
     * 暴露于外面的获取音乐的方法。
     * 先会进行运行时权限的检测，再进行音乐的获取。
     *
     * @return 获取到的音乐
     */
    fun getMusics(): List<Song>? {
        if (mRxPermission == null) {
            mRxPermission = RxPermissions(mActivity)
        }

        mRxPermission?.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                ?.subscribe(ExecuteOnceObserver<Boolean>(
                        onExecuteOnceNext = {
                            LogUtil.d(TAG, "onExecuteOnceNext")
                            if (it) {
                                getMusicsAfterGrant()
                            } else {
                                Toast.makeText(mActivity, mActivity.getString(R.string.get_music_need_permission),
                                        Toast.LENGTH_LONG).show()
                            }
                        }
                ))
        LogUtil.d(TAG, mSongs.toString())
        return mSongs
    }


    /**
     * 这整获取音乐的方法。
     */
    private fun getMusicsAfterGrant() {
        val songs = mutableListOf<Song>()
        val resolver = mActivity.contentResolver
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
                Toast.makeText(mActivity, mActivity.getString(R.string.get_music_failed), Toast.LENGTH_LONG).show()
            }
            !cursor.moveToFirst() -> {
                Toast.makeText(mActivity, mActivity.getString(R.string.none_music), Toast.LENGTH_LONG).show()
            }
            else -> {
                cursor.moveToFirst()

                do {
                    val song = Song()
                    song.isMusic = cursor.getInt(cursor.getColumnIndex(isMusic))
                    song.duration = cursor.getLong(cursor.getColumnIndex(duration))

                    // 略去不是音乐的或者时间小于一分钟的音频
                    if (song.isMusic == 0 || song.duration < 1000 * 60) {
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
        mSongs =  songs
    }

}