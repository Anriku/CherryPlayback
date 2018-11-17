package com.anriku.cherryplayback.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.adapter.SingerDetailAdapter
import com.anriku.cherryplayback.databinding.FragmentSingerDetailBinding
import com.anriku.cherryplayback.utils.extensions.setDivider
import com.anriku.cherryplayback.model.SingerList
import com.anriku.cherryplayback.viewmodel.SingerDetailViewModel

class SingerDetailFragment : BaseFragment() {

    companion object {
        const val SINGER_INFO = "singerInfo"
    }

    private lateinit var mSingerInfo: SingerList.DataBean.ListBean
    private lateinit var mBinding: FragmentSingerDetailBinding
    private lateinit var mSingerDetailViewModel: SingerDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_singer_detail, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFragment()
    }

    private fun initFragment() {
        mSingerInfo = arguments?.getParcelable(SINGER_INFO) ?: return
        val appCompatActivity = activity as? AppCompatActivity ?: return

        mSingerDetailViewModel =
                ViewModelProviders.of(this, SingerDetailViewModel.Factory(mSingerInfo.fsinger_mid))
                    .get(SingerDetailViewModel::class.java)

        appCompatActivity.setSupportActionBar(mBinding.tb)
        appCompatActivity.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = mSingerInfo.fsinger_name
        }

        mSingerDetailViewModel.setSingerImage(mBinding.ivSinger, mSingerInfo)

        mBinding.rv.setDivider()
        val adapter = SingerDetailAdapter(appCompatActivity)
        mBinding.rv.adapter = adapter
        mSingerDetailViewModel.singerDetail.observe(this, Observer(adapter::submitList))
    }

}
