package com.anriku.cherryplayback.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.adapter.LocalMusicAdapter
import com.anriku.cherryplayback.ui.BaseFragment
import com.anriku.cherryplayback.utils.MusicAccessUtil
import kotlinx.android.synthetic.main.fragment_music_list.*

/**
 * Created by anriku on 2018/11/14.
 */

class LocalMusicFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_local_music, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFragment()
    }

    private fun initFragment() {
        context ?: return

        val musicAccessUtil = MusicAccessUtil(context!!)
        rv.adapter = LocalMusicAdapter(
            this.context!!,
            musicAccessUtil.getMusics(this) ?: arrayListOf()
        )
    }

}