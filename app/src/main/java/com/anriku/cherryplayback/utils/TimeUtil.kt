package com.anriku.cherryplayback.utils

import java.lang.StringBuilder

/**
 * Created by anriku on 2018/11/29.
 */

object TimeUtil {

    fun formatTheTime(time: Int): String {
        val min = time / (1000 * 60)
        var second = time % (1000 * 60)
        second /= 1000

        val timeSb = StringBuilder()

        if (min < 10) {
            timeSb.append("0$min:")
        } else {
            timeSb.append("$min:")
        }
        if (second < 10) {
            timeSb.append("0$second")
        } else {
            timeSb.append("$second")
        }
        return timeSb.toString()
    }

}