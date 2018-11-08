package com.anriku.cherryplayback.utils

import android.util.Log
import com.anriku.cherryplayback.BuildConfig

/**
 * Created by anriku on 2018/11/1.
 */

object LogUtil {

    private val IS_DEBUG = BuildConfig.DEBUG

    fun v(tag: String, message: String, throwable: Throwable? = null) {
        if (IS_DEBUG) {
            Log.v(tag, message, throwable)
        }
    }

    fun d(tag: String, message: String, exception: Throwable? = null) {
        if (IS_DEBUG) {
            Log.d(tag, message, exception)
        }
    }

    fun i(tag: String, message: String, exception: Throwable? = null) {
        if (IS_DEBUG) {
            Log.i(tag, message, exception)
        }
    }

    fun w(tag: String, message: String, exception: Throwable? = null) {
        if (IS_DEBUG) {
            Log.w(tag, message, exception)
        }
    }

    fun e(tag: String, message: String, exception: Throwable? = null) {
        if (IS_DEBUG) {
            Log.e(tag, message, exception)
        }
    }
}