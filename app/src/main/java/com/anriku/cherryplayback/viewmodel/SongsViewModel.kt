package com.anriku.cherryplayback.viewmodel

import android.app.Activity
import android.app.Application
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.anriku.cherryplayback.event.ServiceConnectEvent
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.service.MusicService
import com.anriku.cherryplayback.utils.LogUtil
import com.anriku.cherryplayback.utils.MusicAccessUtil
import org.greenrobot.eventbus.EventBus

/**
 * Created by anriku on 2018/10/31.
 */

class SongsViewModel(application: Application) : AndroidViewModel(application) {


    var binder: MusicService.MusicBinder? = null

    companion object {
        private const val TAG = "SongsViewModel"
    }

    private val connection = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName) {}

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            binder = service as MusicService.MusicBinder
            EventBus.getDefault().post(ServiceConnectEvent())
        }
    }

    private lateinit var mMusicAccessUtil: MusicAccessUtil

    fun getSongs(activity: FragmentActivity) {
        mMusicAccessUtil = MusicAccessUtil(activity)
        binder?.setSongs(mMusicAccessUtil.getMusics() ?: mutableListOf())
    }

    fun startAndBindService(activity: FragmentActivity) {
        val intent = Intent(activity, MusicService::class.java)
        ContextCompat.startForegroundService(activity, intent)
        activity.bindService(intent, connection, Activity.BIND_AUTO_CREATE)
    }

    fun stopAndUnbindService(activity: FragmentActivity) {
        val intent = Intent(activity, MusicService::class.java)
        activity.unbindService(connection)
        activity.stopService(intent)
    }

}