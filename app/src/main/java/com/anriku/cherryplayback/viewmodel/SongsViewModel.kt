package com.anriku.cherryplayback.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.utils.MusicAccessUtil

/**
 * Created by anriku on 2018/10/31.
 */

class SongsViewModel : ViewModel() {

    private val mSongs: MutableLiveData<List<Song>> by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<List<Song>>()
    }
    private lateinit var mMusicAccessUtil: MusicAccessUtil

    fun getSongs(context: Context) {
        mMusicAccessUtil = MusicAccessUtil(context)
        mSongs.value = mMusicAccessUtil.getMusics()
    }

}