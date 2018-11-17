package com.anriku.cherryplayback.ui.square

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.adapter.SlideViewAdapter
import com.anriku.cherryplayback.databinding.FragmentMusicSquareBinding
import com.anriku.cherryplayback.event.FragmentReplaceEvent
import com.anriku.cherryplayback.ui.BaseFragment
import com.anriku.cherryplayback.ui.SingerListFragment
import com.anriku.cherryplayback.viewmodel.SongsViewModel
import com.anriku.cherryplayback.viewmodel.SquareViewModel
import org.greenrobot.eventbus.EventBus

/**
 * Created by anriku on 2018/11/14.
 */

class MusicSquareFragment : BaseFragment() {

    private lateinit var mBinding: FragmentMusicSquareBinding
    private lateinit var mSquareViewModel: SquareViewModel

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
        mBinding.setLifecycleOwner(this)

        initFragment()
    }

    private fun initFragment() {
        activity ?: return

        mSquareViewModel = ViewModelProviders.of(activity!!).get(SquareViewModel::class.java)
        mSquareViewModel.getSlides()
        mSquareViewModel.slides.observe(this, Observer {
            mBinding.ssv.slideShowViewAdapter = SlideViewAdapter(it)
        })



    }

    override fun onDestroy() {
        mBinding.ssv.cleanSlideTask()
        super.onDestroy()
    }
}