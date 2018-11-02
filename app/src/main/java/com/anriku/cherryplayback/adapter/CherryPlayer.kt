package com.anriku.cherryplayback.adapter

import android.content.Context
import android.media.MediaPlayer
import com.anriku.cherryplayback.config.PLAY_PATTERN
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.utils.IMusicBinder
import com.anriku.cherryplayback.utils.PlaybackInfoListener
import com.anriku.cherryplayback.utils.PlayerAdapter
import com.anriku.cherryplayback.utils.extensions.getSPValue
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * 具体播放器的实现类
 *
 * Created by anriku on 2018/10/31.
 */

class CherryPlayer(private val mContext: Context) : PlayerAdapter {

    companion object {
        private const val TAG = "CherryPlayer"
        // 刷新操作的时间间隔
        const val UPDATE_INTERVAL = 1000L
    }

    // 当前本地音乐播放到的位置
    private var mCurrentPlayIndex: Int = 0
    // 获取的音乐
    private var mSongs: List<Song> = mutableListOf()

    private var mMediaPlayer: MediaPlayer? = null
    private var mPlaybackInfoListeners: MutableList<PlaybackInfoListener>? = null
    private var mExecutor: ScheduledExecutorService? = null
    private var mUpdateTask: Runnable? = null

    // 记录当前播放的音乐的路径或Uri
    private lateinit var mResourcePath: String
    // 是否当前播放的音乐是在线音乐
    private var mIsOnlineData: Boolean = false

    override fun loadMedia(resourcePath: String, isOnlineData: Boolean) {
        reset()
        initMediaPlayer()

        // 更新当前记录的播放信息
        mResourcePath = resourcePath
        mIsOnlineData = isOnlineData

        // 设置播放源
        mMediaPlayer?.setDataSource(resourcePath)
        // 进行播放准备，如果是在线音乐就进行异步准备。本地音乐的化就直接同步准备
        if (isOnlineData) {
            mMediaPlayer?.prepareAsync()
        } else {
            mMediaPlayer?.prepare()
        }

        initializeProgressCallback()
    }

    override fun loadAnotherMusic(pattern: Int, isNext: Boolean) {
        when (pattern) {
            IMusicBinder.SEQUENCE_PLAY -> {
                if (isNext) {
                    mCurrentPlayIndex++
                    // 防止越界
                    if (mCurrentPlayIndex >= mSongs.size) {
                        mCurrentPlayIndex = mSongs.size - 1
                        return
                    }
                } else {
                    mCurrentPlayIndex--
                    // 防止越界
                    if (mCurrentPlayIndex < 0) {
                        mCurrentPlayIndex = 0
                        return
                    }
                }
                loadLocalMedia(mCurrentPlayIndex)
            }
            IMusicBinder.RANDOM_PLAY -> {
                var randomIndex = Random().nextInt(mSongs.size)
                while (randomIndex == mCurrentPlayIndex) {
                    randomIndex = Random().nextInt(mSongs.size)
                }
                mCurrentPlayIndex = randomIndex
                loadLocalMedia(mCurrentPlayIndex)
            }
            IMusicBinder.SINGLE_PLAY -> {
                loadLocalMedia(mCurrentPlayIndex)
            }
        }
    }

    override fun loadLocalMedia(position: Int) {
        reset()
        mCurrentPlayIndex = position
        mSongs[position].data?.let {
            loadMedia(it, false)
        }
    }

    override fun play() {
        if (!isPlaying()) {
            mMediaPlayer?.start()
            setUpdateTask()
            mPlaybackInfoListeners?.let {
                for (playbackInfoListener in it) {
                    playbackInfoListener.onStateChange(PlaybackInfoListener.PLAY)
                }
            }
        }
    }

    override fun pause() {
        if (isPlaying()) {
            mMediaPlayer?.pause()
            mPlaybackInfoListeners?.let {
                for (playbackInfoListener in it) {
                    playbackInfoListener.onStateChange(PlaybackInfoListener.PAUSE)
                }
            }
        }
    }

    override fun reset() {
        mMediaPlayer?.reset()
        mPlaybackInfoListeners?.let {
            for (playbackInfoListener in it) {
                playbackInfoListener.onStateChange(PlaybackInfoListener.RESET)
            }
        }
        cleanUpdateTask()
    }

    override fun release() {
        mMediaPlayer?.release()
        mMediaPlayer = null
        removeAllPlaybackInfoListener()
        cleanUpdateTask()
    }


    override fun setSongs(songs: List<Song>) {
        mSongs = songs
    }

    override fun getSongs(): List<Song> = mSongs

    override fun isPlaying(): Boolean {
        return mMediaPlayer?.isPlaying ?: false
    }

    override fun seekTo(position: Int) {
        mMediaPlayer?.seekTo(position)
    }

    override fun addPlaybackInfoListener(listener: PlaybackInfoListener) {
        if (mPlaybackInfoListeners == null) {
            mPlaybackInfoListeners = mutableListOf()
        }
        mPlaybackInfoListeners?.add(listener)
        listener.onDurationChanged(mMediaPlayer?.duration ?: 0)
    }

    override fun removePlaybackInfoListener(listener: PlaybackInfoListener) {
        mPlaybackInfoListeners?.remove(listener)
    }

    override fun removeAllPlaybackInfoListener() {
        mPlaybackInfoListeners?.clear()
        mPlaybackInfoListeners = null
    }

    override fun getCurrentPlayIndex(): Int = mCurrentPlayIndex

    /**
     * 用于初始化[android.media.MediaPlayer]
     */
    private fun initMediaPlayer() {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer()
        }
        mMediaPlayer?.setOnCompletionListener {
            // 当前一首播放完成时播放下一首
            val pattern = mContext.getSPValue().getInt(PLAY_PATTERN, IMusicBinder.SEQUENCE_PLAY)
            loadAnotherMusic(pattern)
            play()

            mPlaybackInfoListeners?.let { listeners ->
                for (playbackInfoListener in listeners) {
                    playbackInfoListener.onComplete()
                }
            }
        }
    }

    /**
     * 进行播放的初始化。将音乐播放位置重置到开头
     */
    private fun initializeProgressCallback() {
        mPlaybackInfoListeners?.let {
            for (playbackInfoListener in it) {
                playbackInfoListener.onDurationChanged(mMediaPlayer?.duration ?: 0)
            }
        }
        mMediaPlayer?.seekTo(0)
    }

    /**
     * 设置更新任务
     */
    private fun setUpdateTask() {
        // 任务为空的话就进行任务的创建
        if (mUpdateTask == null) {
            mUpdateTask = Runnable {
                val position = mMediaPlayer?.currentPosition ?: 0
                mPlaybackInfoListeners?.let { listeners ->
                    for (playbackInfoListener in listeners) {
                        playbackInfoListener.onPositionChanged(position)
                    }
                }
            }
        }
        // 线程池为空的话就进行线程池的创建
        if (mExecutor == null) {
            mExecutor = Executors.newSingleThreadScheduledExecutor()
        }
        // 给线程池添加任务
        mExecutor?.scheduleAtFixedRate(mUpdateTask, 0, UPDATE_INTERVAL, TimeUnit.MILLISECONDS)
    }

    /**
     * 清理更新任务
     */
    private fun cleanUpdateTask() {
        mExecutor?.shutdown()
        mExecutor = null
        mUpdateTask = null
    }
}