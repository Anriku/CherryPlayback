package com.anriku.cherryplayback.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.databinding.ActivitySingerDetailBinding

class SingerDetailActivity : AppCompatActivity() {

    companion object {
        const val SINGER_INFO = "singer_info"
    }

    private lateinit var mBinding: ActivitySingerDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_singer_detail)

        initActivity()
    }

    private fun initActivity() {
    }
}
