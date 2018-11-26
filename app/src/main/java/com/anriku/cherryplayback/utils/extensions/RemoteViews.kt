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

    // 注意这里的Flags不能设置为FLAG_UPDATE_CURRENT、FLAG_NO_CURRENT，这样会更新PendingIntent而是只有最后设置的remoteviews点击事件有效
    val pendingIntent: PendingIntent = PendingIntent.getService(
        context.applicationContext, 0, intent,
        PendingIntent.FLAG_NO_CREATE
    )
    setOnClickPendingIntent(widget, pendingIntent)
}