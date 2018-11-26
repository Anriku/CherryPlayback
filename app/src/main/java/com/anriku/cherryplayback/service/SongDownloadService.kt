package com.anriku.cherryplayback.service

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.widget.Toast
import androidx.core.app.JobIntentService

/**
 * Created by anriku on 2018/11/25.
 */

class SongDownloadService : JobIntentService() {

    companion object {
        const val DOWNLOAD_PATH = "com.anriku.download_path"
        const val DESTINATION_PATH = "com.anriku.destination_path"
    }

    private var mDownloadId: Long = 0L
    private val mDownloadManager: DownloadManager by lazy(LazyThreadSafetyMode.NONE) {
        (getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager)
    }
    private var mOnDownloadComplete: (() -> Unit)? = null


    fun getSongDownloadIntent(context: Context, downloadPath: String, destinationPath: String) =
        Intent(context, SongDownloadService::class.java).apply {
            putExtra(DOWNLOAD_PATH, downloadPath)
            putExtra(DESTINATION_PATH, destinationPath)
        }

    override fun onHandleWork(intent: Intent) {
        val downloadPath = intent.getStringExtra(DOWNLOAD_PATH)
        val destinationPath = intent.getStringExtra(DESTINATION_PATH)
        startDownload(downloadPath, destinationPath)
    }

    private fun startDownload(downloadPath: String, destinationPath: String) {
        val uri = Uri.parse(downloadPath)
        val request = DownloadManager.Request(uri)
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setTitle(uri.lastPathSegment)
        request.setVisibleInDownloadsUi(true)
        request.setDestinationInExternalPublicDir(destinationPath, uri.lastPathSegment)
        mDownloadId = mDownloadManager.enqueue(request)
    }

    fun cancelDownload() {
        mDownloadManager.remove(mDownloadId)
    }

    public fun setOnDownloadComplete(downloadComplete: () -> Unit) {
        mOnDownloadComplete = downloadComplete
        val filter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (id == mDownloadId) {
                    mOnDownloadComplete?.invoke()
                    Toast.makeText(this@SongDownloadService, "download success", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@SongDownloadService, "download failed", Toast.LENGTH_LONG).show()
                }
            }
        }, filter)
    }

    fun getStatus(): Int {
        val query = DownloadManager.Query()
        query.setFilterById(mDownloadId)
        val cursor = mDownloadManager.query(query)
        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
        }
        return DownloadManager.ERROR_UNKNOWN
    }
}