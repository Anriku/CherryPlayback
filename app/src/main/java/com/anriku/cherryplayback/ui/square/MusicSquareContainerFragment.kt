package com.anriku.cherryplayback.ui.square

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.event.SquareFragmentReplaceEvent
import com.anriku.cherryplayback.ui.BaseFragment
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by anriku on 2018/11/14.
 */

class MusicSquareContainerFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mine_container, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        replaceFragmentWithBackStack(R.id.fl, MusicSquareFragment())
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun replaceTheFragment(squareFragmentReplaceEvent: SquareFragmentReplaceEvent) {
        val fragment = squareFragmentReplaceEvent.fragment
        if (!fragment.isAdded) {
            replaceFragment(R.id.fl, squareFragmentReplaceEvent.fragment)
        }
    }
}