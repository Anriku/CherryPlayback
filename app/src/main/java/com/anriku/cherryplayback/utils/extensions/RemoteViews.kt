package com.anriku.cherryplayback.utils.extensions

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.annotation.IdRes
import com.anriku.cherryplayback.service.MusicService

/**
 * RemoteViews设置点击事件
 *
 * Created by anriku on 2018/11/19.
 */
fun RemoteViews.setOnclickListener(context: Context, @IdRes widget: Int, action: String) {
    val intent = Intent(context, MusicService::class.java).apply {
        setAction(action)
    }
    val pendingIntent: PendingIntent = PendingIntent.getService(
        context.applicationContext, 0, intent,
        PendingIntent.FLAG_CANCEL_CURRENT
    )
    setOnClickPendingIntent(widget, pendingIntent)
}