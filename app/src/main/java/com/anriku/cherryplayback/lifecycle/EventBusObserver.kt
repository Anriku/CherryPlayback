package com.anriku.cherryplayback.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import org.greenrobot.eventbus.EventBus

/**
 * Created by anriku on 2018/11/1.
 */

class EventBusObserver(private val mLifecycleOwner: LifecycleOwner) : LifecycleObserver {

    companion object {
        private const val TAG = "EventBusObserver"
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun register() {
        if (!EventBus.getDefault().isRegistered(mLifecycleOwner)) {
            EventBus.getDefault().register(mLifecycleOwner)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unregister() {
        if (EventBus.getDefault().isRegistered(mLifecycleOwner)) {
            EventBus.getDefault().unregister(mLifecycleOwner)
        }
    }
}