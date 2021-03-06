package com.anriku.cherryplayback.utils

import android.Manifest
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.config.IS_HAVE_RECORD
import com.anriku.cherryplayback.database.SongsDatabase
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.rxjava.ExecuteOnceObserver
import com.anriku.cherryplayback.utils.extensions.getSPValue
import com.anriku.cherryplayback.utils.extensions.setSPValue
import com.tbruyelle.rxpermissions2.RxPermissions

/**
 * 此类用于获取本地的音乐
 *
 * Created by anriku on 2018/10/31.
 */
class MusicAccessUtil(private val mContext: Context) {

    companion object {
        private const val TAG = "MusicAccessUtil"

        // 取出的音乐各列的index
        const val IS_MUSIC = 0
        const val DURATION = 1
        const val _ID = 2
        const val DATA = 3
        const val SIZE = 4
        const val DISPLAY_NAME = 5
        const val TITLE = 6
        const val DATE_ADDED = 7
        const val DATE_MODIFIED = 8
        const val MIME_TYPE = 9
        const val ARTIST = 10
        const val COMPOSER = 11
        const val YEAR = 12
        const val ALBUM_ID = 13

        // 取出的专辑各列的index
        const val ALBUM_ALBUM_ID = 0
        const val ALBUM = 1
        const val FIRST_YEAR = 2
        const val LAST_YEAR = 3
        const val ALBUM_ART = 4
    }

    private val mAudioProjections: Array<String> by lazy(LazyThreadSafetyMode.NONE) {
        arrayOf(
                android.provider.MediaStore.Audio.Media.IS_MUSIC,
                android.provider.MediaStore.Audio.Media.DURATION,
                android.provider.MediaStore.Audio.Media._ID,
                android.provider.MediaStore.Audio.Media.DATA,
                android.provider.MediaStore.Audio.Media.SIZE,
                android.provider.MediaStore.Audio.Media.DISPLAY_NAME,
                android.provider.MediaStore.Audio.Media.TITLE,
                android.provider.MediaStore.Audio.Media.DATE_ADDED,
                android.provider.MediaStore.Audio.Media.DATE_MODIFIED,
                android.provider.MediaStore.Audio.Media.MIME_TYPE,
                android.provider.MediaStore.Audio.Media.ARTIST,
                android.provider.MediaStore.Audio.Media.COMPOSER,
                android.provider.MediaStore.Audio.Media.YEAR,
                android.provider.MediaStore.Audio.Media.ALBUM_ID
        )
    }
//    private val mAlbumProjections: Array<String> by lazy(LazyThreadSafetyMode.NONE) {
//        arrayOf(
//                android.provider.MediaStore.Audio.Albums.ALBUM_ID,
//                android.provider.MediaStore.Audio.Albums.ALBUM,
//                android.provider.MediaStore.Audio.Albums.FIRST_YEAR,
//                android.provider.MediaStore.Audio.Albums.LAST_YEAR,
//                android.provider.MediaStore.Audio.Albums.ALBUM_ART
//        )
//    }
//    private val mAlbumPlaceholder: Bitmap by lazy(LazyThreadSafetyMode.NONE) {
//        BitmapFactory.decodeResource(mContext.resources, R.drawable.ic_music_placeholder)
//    }

    private val mAudioUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
//    private val mAlbumUri = android.provider.MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
    private var mRxPermission: RxPermissions? = null

    /**
     * 暴露于外面的获取音乐的方法。
     * 先会进行运行时权限的检测，再进行音乐的获取。
     *
     * @param activity 传入activity用于运行时权限检验
     *
     * @return 获取到的音乐
     */
    fun getMusics(activity: FragmentActivity, onGetSongs: (ArrayList<Song>) -> Unit = {}) {
        if (mRxPermission == null) {
            mRxPermission = RxPermissions(activity)
        }

        mRxPermission?.request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        )?.subscribe(ExecuteOnceObserver<Boolean>(
                onExecuteOnceNext = {
                    if (it) {
                        getMusicsAfterGrant(onGetSongs)
                    } else {
                        Toast.makeText(
                                mContext, mContext.getString(R.string.get_music_need_permission),
                                Toast.LENGTH_LONG
                        ).show()
                    }
                }
        ))
    }

    /**
     * 暴露于外面的获取音乐的方法。
     * 先会进行运行时权限的检测，再进行音乐的获取。
     *
     * @param fragment 传入fragment用于运行时权限检验
     *
     * @return 获取到的音乐
     */
    fun getMusics(fragment: Fragment, onGetSongs: (ArrayList<Song>) -> Unit = {}) {
        if (mRxPermission == null) {
            mRxPermission = RxPermissions(fragment)
        }

        mRxPermission?.request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        )
                ?.subscribe(ExecuteOnceObserver<Boolean>(
                        onExecuteOnceNext = {
                            if (it) {
                                getMusicsAfterGrant(onGetSongs)
                            } else {
                                Toast.makeText(
                                        mContext, mContext.getString(R.string.get_music_need_permission),
                                        Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                ))
    }


    /**
     * 这整获取音乐的方法。
     */
    private fun getMusicsAfterGrant(onGetSongs: (ArrayList<Song>) -> Unit) {
        val songs = ArrayList<Song>()
        val resolver = mContext.contentResolver
        val cursor: Cursor? = resolver.query(mAudioUri, mAudioProjections, null, null, null)

        when {
            cursor == null -> {
                Toast.makeText(
                        mContext,
                        mContext.getString(R.string.get_music_failed),
                        Toast.LENGTH_LONG
                ).show()
            }
            !cursor.moveToFirst() -> {
                Toast.makeText(
                        mContext,
                        mContext.getString(R.string.none_music),
                        Toast.LENGTH_LONG
                ).show()
            }
            else -> {
                cursor.moveToFirst()

                do {
                    val song = Song()
                    song.whetherMusic = cursor.getInt(IS_MUSIC)
                    song.duration = cursor.getLong(DURATION)

                    // 略去不是音乐的或者时间小于一分钟的音频
                    if (song.whetherMusic == 0 || song.duration < 1000 * 60) {
                        continue
                    }

                    song.id = cursor.getLong(_ID)
                    song.data = cursor.getString(DATA)
                    song.size = cursor.getLong(SIZE)
                    song.displayName = cursor.getString(DISPLAY_NAME)
                    song.title = cursor.getString(TITLE)
                    song.dateAdded = cursor.getLong(DATE_ADDED)
                    song.dateModified = cursor.getLong(DATE_MODIFIED)
                    song.mineType = cursor.getString(MIME_TYPE)
                    song.artist = cursor.getString(ARTIST)
                    song.composer = cursor.getString(COMPOSER)
                    song.year = cursor.getInt(YEAR)
                    song.albumId = cursor.getLong(ALBUM_ID).toString()

                    songs.add(song)
                } while (cursor.moveToNext())
            }
        }

        cursor?.close()

        // 获取歌曲后回调
        onGetSongs(songs)
    }

//    fun setAlbumBitmap(uri: String, imageView: ImageView): Bitmap {
//        val selectedAudio = Uri.parse(uri)
//        val retriever = MediaMetadataRetriever()
//        retriever.setDataSource(mContext, selectedAudio)
//        val artWork = retriever.embeddedPicture
//
//        return if (artWork == null) {
//            imageView.setImageBitmap(mAlbumPlaceholder)
//            mAlbumPlaceholder
//        } else {
//            val bitmap = BitmapFactory.decodeByteArray(artWork, 0, artWork.size)
//            imageView.setImageBitmap(bitmap)
//            bitmap
//        }
//    }
}