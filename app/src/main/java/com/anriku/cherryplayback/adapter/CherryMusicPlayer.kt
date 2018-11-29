package com.anriku.cherryplayback.adapter

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import com.anriku.cherryplayback.config.PLAY_PATTERN
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.utils.IMusicBinder
import com.anriku.cherryplayback.network.OnlineSongUrl
import com.anriku.cherryplayback.utils.PlaybackInfoListener
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

open class CherryMusicPlayer(protected val mContext: Context) :
    PlayerAdapter {

    companion object {
        private const val TAG = "CherryMusicPlayer"
        // 刷新操作的时间间隔
        const val UPDATE_INTERVAL = 1500L
    }

    // 当前本地音乐播放到的位置
    protected var mCurrentPlayIndex: Int = 0
    // 播放源
    protected var mSongs: List<Song>? = null
    protected var mMediaPlayer: MediaPlayer? = null
    protected var mPlaybackInfoListeners: MutableList<PlaybackInfoListener>? = null
    protected var mExecutor: ScheduledExecutorService? = null
    protected var mUpdateTask: Runnable? = null
    protected val mOnlineSongUrl: OnlineSongUrl by lazy(LazyThreadSafetyMode.NONE) {
        OnlineSongUrl()
    }

    // 记录当前播放的音乐的路径或Uri
    protected lateinit var mResourcePath: String

    override fun loadMedia(resourcePath: String, isOnlyLoad: Boolean, isOnline: Boolean) {
        reset()
        initMediaPlayer(isOnlyLoad)

        if (isOnline) {
            mOnlineSongUrl.getSongUrl(resourcePath, onGetUrl = {
                it ?: return@getSongUrl
                // 设置播放源
                mResourcePath = it
                val aa = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
                mMediaPlayer?.setAudioAttributes(aa)
                mMediaPlayer?.setDataSource(mResourcePath)
                mMediaPlayer?.prepareAsync()
            })
        } else {
            // 更新当前记录的播放信息
            mResourcePath = resourcePath

            // 设置播放源
            mMediaPlayer?.setDataSource(resourcePath)
            mMediaPlayer?.prepare()
        }
    }

    override fun loadAnotherMusic(isNext: Boolean) {
        // 如果播放资源为空就不进行加载
        if (mSongs == null || mSongs!!.isEmpty()) {
            return
        }

        // 当前一首播放完成时播放下一首
        val pattern = mContext.getSPValue().getInt(PLAY_PATTERN, IMusicBinder.SEQUENCE_PLAY)
        when (pattern) {
            IMusicBinder.SEQUENCE_PLAY -> {
                if (isNext) {
                    mCurrentPlayIndex++
                    // 防止越界
                    if (mCurrentPlayIndex >= mSongs!!.size) {
                        mCurrentPlayIndex = mSongs!!.size - 1
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
                loadMediaByPosition(mCurrentPlayIndex)
            }
            IMusicBinder.RANDOM_PLAY -> {
                var randomIndex = Random().nextInt(mSongs!!.size)
                while (randomIndex == mCurrentPlayIndex) {
                    randomIndex = Random().nextInt(mSongs!!.size)
                }
                loadMediaByPosition(randomIndex)
            }
            IMusicBinder.SINGLE_PLAY -> {
                loadMediaByPosition(mCurrentPlayIndex)
            }
        }
    }

    override fun loadMediaByPosition(position: Int, isOnlyLoad: Boolean) {
        // 如果播放资源为空就不进行加载
        if (mSongs == null || mSongs!!.isEmpty()) {
            return
        }

        // 防止数组越界
        mCurrentPlayIndex = when {
            position >= mSongs!!.size -> mSongs!!.size - 1
            position < 0 -> 0
            else -> position
        }

        val isOnline = mSongs!![position].musicType == Song.ONLINE

        mSongs!![position].data?.let {
            loadMedia(it, isOnlyLoad, isOnline)
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
        reset()
        mSongs = songs
    }

    override fun getSongs(): List<Song>? = mSongs

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
        if (mSongs != null && mSongs!!.isNotEmpty()) {
            listener.onLoadMedia(mSongs!![mCurrentPlayIndex])
        }
        if (mMediaPlayer?.isPlaying == true) {
            listener.onStateChange(PlaybackInfoListener.PLAY)
        }
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
    protected fun initMediaPlayer(isOnlyLoad: Boolean) {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer()
        }
        // 初始化完成调用
        mMediaPlayer?.setOnPreparedListener {
            initializeProgressCallback()
            if (!isOnlyLoad) {
                play()
            }
        }
        // 一首歌完后调用
        mMediaPlayer?.setOnCompletionListener {
            loadAnotherMusic()

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
                if (mSongs != null && mSongs!!.isNotEmpty()) {
                    playbackInfoListener.onLoadMedia(mSongs!![mCurrentPlayIndex])
                }
            }
        }
        mMediaPlayer?.seekTo(0)
    }

    /**
     * 设置更新任务
     */
    protected fun setUpdateTask() {
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
    protected fun cleanUpdateTask() {
        mExecutor?.shutdown()
        mExecutor = null
        mUpdateTask = null
    }
}