package com.anriku.cherryplayback.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.adapter.MainVPAdapter
import com.anriku.cherryplayback.databinding.FragmentMainContainerBinding
import com.anriku.cherryplayback.ui.musicroom.MusicRoomFragment
import com.anriku.cherryplayback.ui.musicsquare.MusicSquareFragment
import kotlinx.android.synthetic.main.base_toolbar.*

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
        // 如果要是Fragment中的menu生效需要添加下面的代码
        setHasOptionsMenu(true)

        initFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    private fun initFragment() {
        fragmentManager ?: return
        activity ?: return
        val appComActivity = activity as AppCompatActivity

        appComActivity.setSupportActionBar(tb)
        appComActivity.supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
        }

        // 注意这里用childFragmentManager
        mBinding.vp.adapter = MainVPAdapter(
                childFragmentManager,
                mutableListOf(
                        MusicRoomFragment(),
                        MusicSquareFragment()
                ),
                mutableListOf("音乐室", "音乐广场")
        )
        mBinding.tl.setupWithViewPager(mBinding.vp)
    }

}