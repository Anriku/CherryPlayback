package com.anriku.cherryplayback.ui

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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


    fun setToolbar(toolbar: Toolbar, homeAsUpEnable: Boolean = true, titleEnable: Boolean = false,
                   doWithSupportActionBar: (ActionBar) -> Unit = {}) {

        val appComActivity = activity as AppCompatActivity

        appComActivity.setSupportActionBar(toolbar)
        appComActivity.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(homeAsUpEnable)
            setDisplayShowTitleEnabled(titleEnable)
            doWithSupportActionBar(this)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun puppetFunc(puppetEvent: PuppetEvent) {
    }
}