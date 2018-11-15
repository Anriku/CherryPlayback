package com.anriku.cherryplayback.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.databinding.FragmentMineBinding
import com.anriku.cherryplayback.event.FragmentReplaceEvent
import com.anriku.cherryplayback.ui.BaseFragment
import org.greenrobot.eventbus.EventBus

/**
 * Created by anriku on 2018/11/14.
 */
class MineFragment : BaseFragment() {

    private lateinit var mBinding: FragmentMineBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_mine, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFragment()
    }

    private fun initFragment() {
        mBinding.listeners =
                Listeners(onLocalMusic = {
                    it.findNavController().navigate(R.id.localMusicFragment)
                }, onDownloadMusic = {

                }, onRecentPlay = {

                }, onILike = {

                })
    }


    class Listeners(
            val onLocalMusic: (ImageView) -> Unit = {},
            val onDownloadMusic: (ImageView) -> Unit = {},
            val onRecentPlay: (ImageView) -> Unit = {},
            val onILike: (ImageView) -> Unit = {}
    ) {

        fun onLocalMusicClick(view: View) {
            onLocalMusic(view as ImageView)
        }

        fun onDownloadMusicClick(view: View) {
            onDownloadMusic(view as ImageView)
        }

        fun onRecentPlayClick(view: View) {
            onRecentPlay(view as ImageView)
        }

        fun onILikeClick(view: View) {
            onILike(view as ImageView)
        }

    }
}