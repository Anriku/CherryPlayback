package com.anriku.cherryplayback.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.adapter.LocalMusicAdapter
import com.anriku.cherryplayback.ui.BaseFragment
import com.anriku.cherryplayback.utils.MusicAccessUtil
import kotlinx.android.synthetic.main.base_toolbar.*
import kotlinx.android.synthetic.main.fragment_local_music.*

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
        activity ?: return
        val appComActivity = activity as AppCompatActivity

        appComActivity.setSupportActionBar(tb)
        appComActivity.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        val musicAccessUtil = MusicAccessUtil(activity!!)
        musicAccessUtil.getMusics(this) {
            rv.adapter = LocalMusicAdapter(
                    activity!!,
                    it
            )
        }

        tb.findViewById<TextView>(R.id.title).text = "本地音乐"
    }

}