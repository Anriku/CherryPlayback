package com.anriku.cherryplayback.network

import com.anriku.cherryplayback.model.SongVKey
import com.anriku.cherryplayback.rxjava.ExecuteOnceObserver
import com.anriku.cherryplayback.utils.extensions.errorHandler
import com.anriku.cherryplayback.utils.extensions.setSchedulers

/**
 * Created by anriku on 2018/11/8.
 */

class OnlineSongUrl {

    private val mMusicService: QQMusicService by lazy(LazyThreadSafetyMode.NONE) {
        ApiGenerate.getGsonApiService(QQMusicService::class.java)
    }

    /**
     * 获取在线歌曲的Url。
     *
     * @param songMid 歌曲的mid
     * @param onGetUrl 回掉函数对象
     */
    fun getSongUrl(songMid: String, onGetUrl: (String?) -> Unit) {
        mMusicService.getSongVKey(songMid, "C400$songMid.m4a")
                .errorHandler()
                .setSchedulers()
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                    val songUrl = compoundSongUrl(it)
                    onGetUrl(songUrl)
                }))
    }

    /**
     * 合成歌曲的Url
     *
     * @param songVKey 歌曲的vKey
     */
    private fun compoundSongUrl(songVKey: SongVKey): String? {
        songVKey.data?.items?.get(0) ?: return null

        return "$BASE_SONG${songVKey.data!!.items!![0].filename}?" +
                "vkey=${songVKey.data!!.items!![0].vkey}" +
                "&guid=$GUID" +
                "&fromtag=66"
    }

}