package com.anriku.cherryplayback.ui.square

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.databinding.FragmentMusicSquareBinding
import com.anriku.cherryplayback.event.FragmentReplaceEvent
import com.anriku.cherryplayback.ui.BaseFragment
import com.anriku.cherryplayback.ui.SingerListFragment
import org.greenrobot.eventbus.EventBus

/**
 * Created by anriku on 2018/11/14.
 */

class MusicSquareFragment : BaseFragment() {

    private lateinit var mBinding: FragmentMusicSquareBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_music_square, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.listeners = Listeners(
            onSinger = {
                it.findNavController().navigate(R.id.singerListFragment)
            }, onRank = {

            }
        )
    }

    class Listeners(
        val onSinger: (ImageView) -> Unit = {},
        val onRank: (ImageView) -> Unit = {}
    ) {

        fun onSingerClick(view: View) {
            onSinger(view as ImageView)
        }

        fun onRankClick(view: View) {
            onRank(view as ImageView)
        }

    }

}