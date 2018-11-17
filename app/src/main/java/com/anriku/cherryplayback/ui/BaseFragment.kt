package com.anriku.cherryplayback.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.event.PuppetEvent
import com.anriku.cherryplayback.lifecycle.EventBusObserver
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by anriku on 2018/11/14.
 */

open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(EventBusObserver(this))
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(R.transition.change_image_transform)
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(R.transition.change_image_transform)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun puppetFunc(puppetEvent: PuppetEvent) {
    }
}