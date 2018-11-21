package com.anriku.cherryplayback.utils

import android.view.View
import com.anriku.cherryplayback.config.MUSIC_SWITCH_INTERVAL

/**
 * 防止按键的连续点击
 *
 * Created by anriku on 2018/11/21.
 */
class SafeOnclickListener(val onSafeClick: (View) -> Unit) : (View) -> Unit {

    var lastTime = 0L

    override fun invoke(view: View) {

        val time = System.currentTimeMillis()
        if (time - lastTime > MUSIC_SWITCH_INTERVAL) {
            onSafeClick(view)
            lastTime = time
        }
    }
}