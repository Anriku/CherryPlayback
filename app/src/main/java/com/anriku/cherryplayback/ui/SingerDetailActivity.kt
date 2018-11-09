package com.anriku.cherryplayback.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.adapter.SingerDetailAdapter
import com.anriku.cherryplayback.databinding.ActivitySingerDetailBinding
import com.anriku.cherryplayback.model.SingerList
import com.anriku.cherryplayback.viewmodel.SingerDetailViewModel

class SingerDetailActivity : AppCompatActivity() {

    companion object {
        const val SINGER_INFO = "singer_info"
    }

    private lateinit var mSingerInfo: SingerList.DataBean.ListBean
    private lateinit var mBinding: ActivitySingerDetailBinding
    private lateinit var mSingerDetailViewModel: SingerDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_singer_detail)

        initActivity()
    }

    private fun initActivity() {
        mSingerInfo = intent.getParcelableExtra(SINGER_INFO)

        mSingerDetailViewModel =
                ViewModelProviders.of(this, SingerDetailViewModel.Factory(mSingerInfo.fsinger_mid))
                    .get(SingerDetailViewModel::class.java)

        val adapter = SingerDetailAdapter(this)
        mBinding.rv.adapter = adapter
        mSingerDetailViewModel.singerDetail.observe(this, Observer(adapter::submitList))

    }
}
