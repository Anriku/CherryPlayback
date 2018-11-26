package com.anriku.cherryplayback.utils

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.annotation.LayoutRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.anriku.cherryplayback.R


/**
 * Created by anriku on 2018/11/19.
 */

class NotificationUtil(private val mContext: Context) {

    private lateinit var mLargeRemoteViews: RemoteViews
    private lateinit var mSmallRemoteViews: RemoteViews
    lateinit var notification: Notification
    val notificationManagerCompat: NotificationManagerCompat by lazy(LazyThreadSafetyMode.NONE)
    { NotificationManagerCompat.from(mContext) }

    /**
     * 用于创建通知
     */
    fun createNotification(clazz: Class<*>, @LayoutRes smallLayout: Int, @LayoutRes largeLayout: Int, channelId: String)
            : Notification {

        mSmallRemoteViews = RemoteViews(mContext.packageName, smallLayout)
        mLargeRemoteViews = RemoteViews(mContext.packageName, largeLayout)

        val notificationIntent = Intent(mContext, clazz)
        val pendingIntent = PendingIntent.getActivity(
            mContext, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        notification = NotificationCompat.Builder(mContext, channelId)
            .setWhen(System.currentTimeMillis())
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_music_placeholder)
            .setOngoing(true)
            .setCustomContentView(mSmallRemoteViews)
            .setCustomBigContentView(mLargeRemoteViews)
            .build()

        return notification
    }

    fun setActionOnRemoteViews(
        notificationId: Int, onSmallActions: (RemoteViews) -> Unit,
        onLargeActions: (RemoteViews) -> Unit
    ) {
        onSmallActions(mSmallRemoteViews)
        onLargeActions(mLargeRemoteViews)
        notifyNotification(notificationId)
    }

    fun notifyNotification(notificationId: Int) {
        notificationManagerCompat.notify(notificationId, notification)
    }
}