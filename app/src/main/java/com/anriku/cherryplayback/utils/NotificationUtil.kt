package com.anriku.cherryplayback.utils

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.annotation.LayoutRes
import androidx.core.app.NotificationCompat
import com.anriku.cherryplayback.R

/**
 * Created by anriku on 2018/11/19.
 */

class NotificationUtil {

    private lateinit var mSmallRemoteViews: RemoteViews
    private lateinit var mLargeRemoteViews: RemoteViews

    /**
     * 用于创建通知
     */
    fun createNotification(
        context: Context, clazz: Class<*>,
        @LayoutRes smallLayout: Int, @LayoutRes largeLayout: Int
        , channelId: String
    ): Notification {
        mSmallRemoteViews = RemoteViews(context.packageName, smallLayout)
        mLargeRemoteViews = RemoteViews(context.packageName, largeLayout)

        val notificationIntent = Intent(context, clazz)
        val pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT)

        return NotificationCompat.Builder(context, channelId)
            .setWhen(System.currentTimeMillis())
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_music_placeholder)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(mSmallRemoteViews)
            .setCustomBigContentView(mLargeRemoteViews)
            .build()
    }

    fun setActionOnSmallRemoteViews(onActions: (RemoteViews) -> Unit) {
        onActions(mSmallRemoteViews)
    }

    fun setActionOnLargeRemoteViews(onActions: (RemoteViews) -> Unit) {
        onActions(mLargeRemoteViews)
    }
}