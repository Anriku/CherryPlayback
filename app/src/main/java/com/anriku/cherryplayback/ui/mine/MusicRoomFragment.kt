package com.anriku.cherryplayback.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.databinding.FragmentRoomBinding
import com.anriku.cherryplayback.ui.BaseFragment
import com.anriku.cherryplayback.viewmodel.MusicRoomViewModel

/**
 * Created by anriku on 2018/11/14.
 */
class MusicRoomFragment : BaseFragment() {

    companion object {
        const val TAG = "MusicRoomFragment"
    }

    private lateinit var mBinding: FragmentRoomBinding
    private lateinit var mMinViewModel: MusicRoomViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_room, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFragment()
    }

    private fun initFragment() {
        activity ?: return

        mMinViewModel = ViewModelProviders.of(activity!!).get(MusicRoomViewModel::class.java)
        mMinViewModel.getDailyPic(mBinding.ivDailyPic)

        mBinding.listeners =
                MusicRoomFragment.Listeners(onLocalMusic = {
                    it.findNavController().navigate(R.id.action_main_container_fragment_to_local_music_fragment)
                }, onDownloadMusic = {

                }, onRecentPlay = {

                }, onSinger = {
                    it.findNavController().navigate(R.id.action_main_container_fragment_to_singer_list_fragment)
                }, onILike = {

                }, onRank = {

                })
    }


    class Listeners(
        val onLocalMusic: (ImageView) -> Unit = {},
        val onDownloadMusic: (ImageView) -> Unit = {},
        val onRecentPlay: (ImageView) -> Unit = {},
        val onSinger: (ImageView) -> Unit = {},
        val onILike: (ImageView) -> Unit = {},
        val onRank: (ImageView) -> Unit = {}
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


        fun onSingerClick(view: View) {
            onSinger(view as ImageView)
        }

        fun onRankClick(view: View) {
            onRank(view as ImageView)
        }

    }
}