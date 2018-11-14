package com.anriku.cherryplayback.viewmodel

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.databinding.ActivityControlBinding
import com.anriku.cherryplayback.event.ServiceConnectEvent
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.service.MusicService
import com.anriku.cherryplayback.utils.IMusicBinder
import com.anriku.cherryplayback.utils.MusicAccessUtil
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.textColor

/**
 * Created by anriku on 2018/10/31.
 */

open class SongsViewModel : ViewModel() {

    val currentPlaySongName: MutableLiveData<String> = MutableLiveData()
    val currentPlayArtist: MutableLiveData<String> = MutableLiveData()

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

//    /**
//     * 获取音乐
//     *
//     * @param activity 调用此方法需要一个[androidx.fragment.app.FragmentActivity].因为[MusicAccessUtil]
//     * 获取需要进行运行时权限的检测。
//     */
//    fun setSongs(activity: FragmentActivity): So {
//        val intent = activity.intent
//        val musicAccessUtil = MusicAccessUtil(activity)
//        binder?.setSongs(musicAccessUtil.getMusics() ?: mutableListOf(), false)
//    }

    /**
     * 进行音乐服务的启用以及访问
     *
     * @param activity 启动服务的Activity
     */
    fun startAndBindService(activity: FragmentActivity) {
        val intent = Intent(activity, MusicService::class.java)
        ContextCompat.startForegroundService(activity, intent)
        activity.bindService(intent, connection, Activity.BIND_AUTO_CREATE)
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
     * @param binding ActivityControlBinding
     */
    fun onLoadMedia(activity: FragmentActivity, song: Song, binding: ActivityControlBinding) {
        val musicAccessUtil = MusicAccessUtil(activity)

        currentPlaySongName.value = song.title
        currentPlayArtist.value = song.artist

//        song.data?.let {
//            val bitmap = musicAccessUtil.setAlbumBitmap(it, binding.ivAlbum)
//            binding.cl.setBackgroundColor(PaletteUtil.extractBackgroundColor(bitmap))
//            val textColor = PaletteUtil.extractTextColor(bitmap)
//            binding.tvSongName.textColor = textColor
//            binding.tvArtist.textColor = textColor
//            return
//        }
        binding.cl.setBackgroundColor(ContextCompat.getColor(activity, R.color.default_bk_color))
        val textColor = ContextCompat.getColor(activity, R.color.default_text_color)
        binding.tvSongName.textColor = textColor
        binding.tvArtist.textColor = textColor
    }
}