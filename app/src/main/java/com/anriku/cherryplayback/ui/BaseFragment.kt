package com.anriku.cherryplayback.ui

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
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
    }

    protected fun replaceFragment(@IdRes container: Int, fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(container, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    protected fun replaceFragmentWithBackStack(@IdRes container: Int, fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(container, fragment)
            ?.commit()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun puppetFunc(puppetEvent: PuppetEvent){

    }
}