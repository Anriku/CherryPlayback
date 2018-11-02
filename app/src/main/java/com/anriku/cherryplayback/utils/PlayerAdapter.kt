package com.anriku.cherryplayback.utils

import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.utils.IMusicBinder.Companion.RANDOM_PLAY
import com.anriku.cherryplayback.utils.IMusicBinder.Companion.SEQUENCE_PLAY
import com.anriku.cherryplayback.utils.IMusicBinder.Companion.SINGLE_PLAY

/**
 * 播放器类需要实现的接口
 *
 * Created by anriku on 2018/10/31.
 */
interface PlayerAdapter {

    /**
     * 给播放器添加需要获取播放信息的接口类。
     *
     * @param listener 想获取播放信息的类
     */
    abstract fun addPlaybackInfoListener(listener: PlaybackInfoListener)

    /**
     * 移除对应的播放器类
     *
     * @param listener 要被移除的获取播放信息的类
     */
    abstract fun removePlaybackInfoListener(listener: PlaybackInfoListener)

    /**
     * 移除所有的播放器类
     *
     * @param listener 想获取播放信息的类
     */
    abstract fun removeAllPlaybackInfoListener()

    /**
     * 用于切换音乐的。下面的情况下进行调用
     * 1. 点击上一首切换
     * 2. 点击下一首切换
     * 3. 一首歌完了进行切换
     *
     * @param pattern 播放的模式。有[SEQUENCE_PLAY]、[RANDOM_PLAY]、[SINGLE_PLAY]三种模式
     * @param isNext 如果是[SEQUENCE_PLAY]模式这个参数表示是下一首还是上一首。
     */
    abstract fun loadAnotherMusic(pattern: Int, isNext: Boolean = true)

    /**
     * 加载本地音乐列表指定的音乐。
     *
     * @param position 要播放的音乐在本地音乐列表的中的位置
     */
    abstract fun loadLocalMedia(position: Int)

    /**
     * 根据本地音乐的路径或者在线音乐的Uri进行音乐的加载。
     *
     * @param resourcePath 音乐路径或者Uri
     * @param isOnlineData 是否是在线音乐
     */
    abstract fun loadMedia(resourcePath: String, isOnlineData: Boolean)

    abstract fun play()

    abstract fun pause()

    abstract fun reset()

    abstract fun isPlaying(): Boolean

    abstract fun seekTo(position: Int)

    abstract fun release()

    abstract fun setSongs(songs: List<Song>)

    abstract fun getSongs(): List<Song>

    abstract fun getCurrentPlayIndex(): Int

}