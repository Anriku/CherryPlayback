package com.anriku.cherryplayback.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.event.MineFragmentReplaceEvent
import com.anriku.cherryplayback.ui.BaseFragment
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by anriku on 2018/11/14.
 */

class MineContainerFragment : BaseFragment() {

    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_mine_container, container, false)
        return mView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        replaceFragmentWithBackStack(R.id.fl, MineFragment())
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun replaceTheFragment(mineFragmentReplaceEvent: MineFragmentReplaceEvent) {
        val fragment = mineFragmentReplaceEvent.fragment
        if (!fragment.isAdded) {
            replaceFragment(R.id.fl, mineFragmentReplaceEvent.fragment)
        }
    }
}