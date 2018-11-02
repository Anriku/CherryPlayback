package com.anriku.cherryplayback.viewmodel

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.anriku.cherryplayback.event.ServiceConnectEvent
import com.anriku.cherryplayback.service.MusicService
import com.anriku.cherryplayback.utils.IMusicBinder
import com.anriku.cherryplayback.utils.LogUtil
import com.anriku.cherryplayback.utils.MusicAccessUtil
import org.greenrobot.eventbus.EventBus

/**
 * Created by anriku on 2018/10/31.
 */

class SongsViewModel : ViewModel() {

    companion object {
        private const val TAG = "SongsViewModel"
    }

    private lateinit var mMusicAccessUtil: MusicAccessUtil
    // 用于与播放音乐的服务进行通信
    var binder: IMusicBinder? = null
    // 绑定服务的ServiceConnection
    private val connection = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName) {}

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            LogUtil.d(TAG, "onServiceConnect")
            binder = service as IMusicBinder
            EventBus.getDefault().post(ServiceConnectEvent())
        }
    }

    /**
     * 获取音乐
     *
     * @param activity 调用此方法需要一个[androidx.fragment.app.FragmentActivity].因为[MusicAccessUtil]
     * 获取需要进行运行时权限的检测。
     */
    fun getSongs(activity: FragmentActivity) {
        mMusicAccessUtil = MusicAccessUtil(activity)
        binder?.setSongs(mMusicAccessUtil.getMusics() ?: mutableListOf())
    }

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
}