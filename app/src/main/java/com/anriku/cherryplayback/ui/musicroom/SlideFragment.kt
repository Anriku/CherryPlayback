package com.anriku.cherryplayback.ui.musicroom

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.databinding.DataBindingUtil
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.databinding.FragmentSlideBinding
import com.anriku.cherryplayback.ui.BaseFragment

/**
 * Created by anriku on 2018/11/28.
 */

class SlideFragment : BaseFragment() {

    companion object {
        const val TAG = "SlideFragment"
        const val URL = "url"
    }
    private lateinit var mBinding: FragmentSlideBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_slide, container, false)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFragment()
    }

    private fun initFragment() {
        val url = arguments?.getString(URL)

        url?.let {
            mBinding.wv.apply {
                loadUrl(url)
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
            }

        }
    }
}