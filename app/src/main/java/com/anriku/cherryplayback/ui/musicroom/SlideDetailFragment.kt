package com.anriku.cherryplayback.ui.musicroom

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.databinding.DataBindingUtil
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.databinding.FragmentSlideDetailBinding
import com.anriku.cherryplayback.ui.BaseFragment

/**
 * Created by anriku on 2018/11/28.
 */

class SlideDetailFragment : BaseFragment() {

    companion object {
        const val TAG = "SlideDetailFragment"
        const val URL = "url"
    }
    private lateinit var mBinding: FragmentSlideDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_slide_detail, container, false)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFragment()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initFragment() {
        setToolbar(mBinding.abl.findViewById(R.id.tb))

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