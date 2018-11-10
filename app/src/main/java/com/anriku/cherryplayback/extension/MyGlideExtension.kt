package com.anriku.cherryplayback.extension

import android.annotation.SuppressLint
import com.anriku.cherryplayback.R
import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.request.RequestOptions

/**
 * Created by anriku on 2018/11/10.
 */

@GlideExtension
object MyGlideExtension {

    private const val MINI_THUMB_SIZE = 100

    @SuppressLint("CheckResult")
    @GlideOption
    @JvmStatic
    fun miniThumb(options: RequestOptions) {
        options
            .fitCenter()
            .override(MINI_THUMB_SIZE)
            .placeholder(R.drawable.ic_singer)
            .error(R.drawable.ic_error)
    }



}