package com.anriku.cherryplayback.adapter

import android.content.Context
import android.media.AudioAttributes
import com.anriku.cherryplayback.utils.OnlineSongUtil


/**
 * Created by anriku on 2018/11/13.
 */

class OnlineMusicPlayer(context: Context) : LocalMusicPlayer(context) {

    companion object {
        private const val TAG = "OnlineMusicPlayer"
    }

    override fun loadMedia(resourcePath: String) {
        reset()
        initMediaPlayer(isOnlyLoad)

        // 更新当前记录的播放信息
        mResourcePath = OnlineSongUtil.constructUrl(resourcePath)

        // 设置播放源
        mMediaPlayer?.setDataSource(mResourcePath)
        val aa = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()
        mMediaPlayer?.setAudioAttributes(aa)

        mMediaPlayer?.prepareAsync()
    }

}