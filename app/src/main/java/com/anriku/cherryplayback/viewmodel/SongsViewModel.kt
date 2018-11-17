package com.anriku.cherryplayback.viewmodel

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anriku.cherryplayback.config.IS_HAVE_RECORD
import com.anriku.cherryplayback.config.LAST_PLAY_INDEX
import com.anriku.cherryplayback.database.SongsDatabase
import com.anriku.cherryplayback.event.ServiceConnectEvent
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.network.*
import com.anriku.cherryplayback.rxjava.ExecuteOnceObserver
import com.anriku.cherryplayback.service.MusicService
import com.anriku.cherryplayback.utils.IMusicBinder
import com.anriku.cherryplayback.utils.LogUtil
import com.anriku.cherryplayback.utils.MusicAccessUtil
import com.anriku.cherryplayback.utils.extensions.errorHandler
import com.anriku.cherryplayback.utils.extensions.getSPValue
import com.anriku.cherryplayback.utils.extensions.setSchedulers
import com.bumptech.glide.Glide
import org.greenrobot.eventbus.EventBus
import java.net.URLEncoder
import java.nio.charset.Charset
import java.util.ArrayList

/**
 * Created by anriku on 2018/10/31.
 */

open class SongsViewModel : ViewModel() {

    val currentPlaySongName: MutableLiveData<String> = MutableLiveData()
    val currentPlayArtist: MutableLiveData<String> = MutableLiveData()

    companion object {
        private const val TAG = "SongsViewModel"
    }

    // 用于与播放音乐的服务进行通信
    var binder: IMusicBinder? = null
    // 绑定服务的ServiceConnection
    protected val connection = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName) {}

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            binder = service as IMusicBinder
            EventBus.getDefault().post(ServiceConnectEvent())
        }
    }
    private val mQQMusicService: QQMusicService by lazy(LazyThreadSafetyMode.NONE) {
        ApiGenerate.getGsonApiService(QQMusicService::class.java)
    }
    private val mLyricService: LyricService by lazy(LazyThreadSafetyMode.NONE) {
        ApiGenerate.getXMLApiService(LyricService::class.java, BASE_LYRIC)
    }

    /**
     * 进行音乐服务的启用以及访问
     *
     * @param activity 启动服务的Activity
     */
    fun bindService(activity: FragmentActivity) {
        val intent = Intent(activity, MusicService::class.java)
        activity.bindService(intent, connection, Activity.BIND_AUTO_CREATE)
    }


    fun firstStartService(activity: FragmentActivity) {
        if (binder?.getSongs() == null) {
            val isHaveRecord = activity.getSPValue().getBoolean(IS_HAVE_RECORD, false)
            if (isHaveRecord) {
                val database = SongsDatabase.getDatabase(activity.applicationContext)
                database.songDao().getAllSongs().toObservable()
                    .setSchedulers()
                    .errorHandler()
                    .subscribe(ExecuteOnceObserver(onExecuteOnceNext = { songsFromDatabase ->
                        startService(activity, songsFromDatabase as ArrayList<Song>)

                    }, onExecuteOnceError = { _ ->
                        val musicAccessUtil = MusicAccessUtil(activity)
                        musicAccessUtil.getMusics(activity) {
                            startService(activity, it)
                        }
                    }))
            } else {
                val musicAccessUtil = MusicAccessUtil(activity)
                musicAccessUtil.getMusics(activity) {
                    startService(activity, it)
                }
            }
        }
    }

    private fun startService(activity: FragmentActivity, songs: ArrayList<Song>) {
        val intent = Intent(activity, MusicService::class.java)
        intent.putParcelableArrayListExtra(MusicService.SONGS, songs)
        intent.putExtra(MusicService.IS_ONLY_LOAD, true)
        intent.putExtra(MusicService.PLAY_INDEX, activity.getSPValue().getInt(LAST_PLAY_INDEX, 0))
        ContextCompat.startForegroundService(activity, intent)
    }

    /**
     * 进行音乐服务的解绑以及停用
     *
     * @param activity 停用服务的Activity
     */
    fun stopAndUnbindService(activity: FragmentActivity) {
        val intent = Intent(activity, MusicService::class.java)
        activity.unbindService(connection)
        activity.stopService(intent)
        activity.finish()
    }

    /**
     * 进行音乐服务的解绑
     *
     * @param activity 解绑服务的Activity
     */
    fun unbindService(activity: FragmentActivity) {
        activity.unbindService(connection)
    }

    /**
     * 在音乐被加载的时候显示歌曲的名称、歌手、专辑图片的信息
     *
     * @param activity [FragmentActivity]
     * @param song 被加载的歌曲
     */
    fun onLoadMedia(activity: FragmentActivity, song: Song, imageView: ImageView, width: Int = 500) {
        currentPlaySongName.value = song.title
        currentPlayArtist.value = song.artist

        mLyricService.getLyric(song.id % 100, song.id)
            .errorHandler()
            .setSchedulers()
            .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                LogUtil.d(TAG, it.lyric)
            }))
        if (song.musicType == Song.ONLINE) {
            Glide.with(imageView.context).load(ImageUrl.getAlbumImageUrl(song.albumId?.toLong() ?: -1))
                .into(imageView)
        } else {
            val searchKey = "${song.title}-${song.artist}"

            mQQMusicService
                .search(searchKey, 1, 1)
                .setSchedulers()
                .errorHandler()
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = { searchResult ->
                    Glide.with(imageView.context)
                        .load(ImageUrl.getAlbumImageUrl(searchResult.data.song.list[0].album.id.toLong(), width))
                        .into(imageView)
                }))
        }
    }

}