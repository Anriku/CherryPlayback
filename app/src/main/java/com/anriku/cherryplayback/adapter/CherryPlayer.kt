package com.anriku.cherryplayback.adapter

import android.content.Context
import android.media.MediaPlayer
import com.anriku.cherryplayback.utils.PlaybackInfoListener
import com.anriku.cherryplayback.utils.PlayerAdapter
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * Created by anriku on 2018/10/31.
 */

class CherryPlayer(private val mContext: Context) : PlayerAdapter {

    companion object {
        const val UPDATE_INTERVAL = 1000L
    }

    private var mMediaPlayer: MediaPlayer? = null
    private var mPlaybackInfoListener: PlaybackInfoListener? = null
    private var mExecutor: ScheduledExecutorService? = null
    private var mUpdateTask: Runnable? = null

    private lateinit var mResourcePath: String
    private var mIsOnlineData: Boolean = false

    override fun loadMedia(resourcePath: String, isOnlineData: Boolean) {
        initMediaPlayer()

        mResourcePath = resourcePath
        mIsOnlineData = isOnlineData

        mMediaPlayer?.setDataSource(resourcePath)
        if (isOnlineData) {
            mMediaPlayer?.prepareAsync()
        } else {
            mMediaPlayer?.prepare()
        }

        initializeProgressCallback()
    }

    override fun play() {
        if (!isPlaying()) {
            mMediaPlayer?.start()
            setUpdateTask()
            mPlaybackInfoListener?.onStateChange(PlaybackInfoListener.PLAYING)
        }
    }

    override fun pause() {
        if (isPlaying()) {
            mMediaPlayer?.pause()
            mPlaybackInfoListener?.onStateChange(PlaybackInfoListener.PAUSED)
        }
    }

    override fun reset() {
        mMediaPlayer?.reset()
        loadMedia(mResourcePath, mIsOnlineData)
        cleanUpdateTask()
    }

    override fun release() {
        mMediaPlayer?.release()
        mMediaPlayer = null
        cleanUpdateTask()
    }

    override fun isPlaying(): Boolean {
        return mMediaPlayer?.isPlaying ?: false
    }

    override fun initializeProgressCallback() {
        mPlaybackInfoListener?.onDurationChanged(mMediaPlayer?.duration ?: 0)
        mMediaPlayer?.seekTo(0)
    }

    override fun seekTo(position: Int) {
        mMediaPlayer?.seekTo(position)
    }

    private fun initMediaPlayer() {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer()
        }
        mMediaPlayer?.setOnCompletionListener {
            mPlaybackInfoListener?.onComplete()
        }
    }

    private fun setUpdateTask() {
        if (mUpdateTask == null) {
            mUpdateTask = Runnable {
                val position = mMediaPlayer?.currentPosition ?: 0
                mPlaybackInfoListener?.onPositionChanged(position)
            }
        }

        if (mExecutor == null) {
            mExecutor = Executors.newSingleThreadScheduledExecutor()
        }
        mExecutor?.scheduleAtFixedRate(mUpdateTask, 0,
            UPDATE_INTERVAL, TimeUnit.MILLISECONDS)
    }

    private fun cleanUpdateTask() {
        mExecutor?.shutdown()
        mExecutor = null
        mUpdateTask = null
    }


}