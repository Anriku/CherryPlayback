package com.anriku.cherryplayback.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.adapter.MainVPAdapter
import com.anriku.cherryplayback.databinding.FragmentMainContainerBinding
import com.anriku.cherryplayback.ui.mine.MineFragment
import com.anriku.cherryplayback.ui.square.MusicSquareFragment

/**
 * Created by anriku on 2018/11/15.
 */

class MainContainerFragment : BaseFragment() {

    private lateinit var mBinding: FragmentMainContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main_container, container, false
        )
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFragment()
    }

    private fun initFragment() {
        fragmentManager ?: return

        // 注意这里用childFragmentManager
        mBinding.vp.adapter = MainVPAdapter(
            childFragmentManager,
            mutableListOf(
                MineFragment(),
                MusicSquareFragment()
            ),
            mutableListOf("我的", "音乐广场")
        )
        mBinding.tl.setupWithViewPager(mBinding.vp)
    }

}