package com.anriku.cherryplayback.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.anriku.cherryplayback.event.PuppetEvent
import com.anriku.cherryplayback.lifecycle.EventBusObserver
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by anriku on 2018/11/7.
 */

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(EventBusObserver(this))

//        if (Build.VERSION.SDK_INT >= 21) {
        val decorView = window.decorView
        val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        decorView.systemUiVisibility = option
//        } else if (Build.VERSION.SDK_INT >= 19) {
//            window.setFlags(
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//            )
//        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun puppet(puppetEvent: PuppetEvent) {

    }


    protected fun replaceFragment(@IdRes container: Int, fragment: Fragment) {
        val transaction = supportFragmentManager?.beginTransaction()
        transaction?.replace(container, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    protected fun replaceFragmentWithStack(@IdRes container: Int, fragment: Fragment) {
        val transaction = supportFragmentManager?.beginTransaction()
        transaction?.replace(container, fragment)
            ?.commit()
    }

    protected fun onBackNavigationPressed() {
        val fm = supportFragmentManager
        fm?.popBackStack()
        if (fm.backStackEntryCount > 0) {
            fm.popBackStack()
        }
    }
}