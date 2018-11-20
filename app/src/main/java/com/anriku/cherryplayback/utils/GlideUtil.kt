package com.anriku.cherryplayback.utils

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 * Created by anriku on 2018/11/20.
 */

object GlideUtil {

    fun getBitmap(context: Context, url: String, onGet: (Bitmap?) -> Unit,
                  onFail: (GlideException?) -> Unit = {}) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                        onFail(e)
                        return true
                    }

                    override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        onGet(resource)
                        return true
                    }

                }).submit()
    }
}