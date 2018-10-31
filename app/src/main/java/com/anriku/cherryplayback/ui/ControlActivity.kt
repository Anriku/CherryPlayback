package com.anriku.cherryplayback.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.databinding.ActivityControlBinding

class ControlActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityControlBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_control)

        initControlActivity()
    }

    private fun initControlActivity() {
        mBinding.listeners = ControlActivityListener(onPattern = {

        })
    }


    class ControlActivityListener(
        val onPattern: (ImageView) -> Unit = {},
        val onPrevious: (ImageView) -> Unit = {},
        val onPlayAndPause: (ImageView) -> Unit = {},
        val onNext: (ImageView) -> Unit = {},
        val onList: (ImageView) -> Unit = {}
    ) {

        fun onPatternClick(view: View) {
            onPattern(view as ImageView)
        }

        fun onPreviousClick(view: View) {
            onPrevious(view as ImageView)
        }

        fun onPlayAndPauseClick(view: View) {
            onPlayAndPause(view as ImageView)
        }

        fun onNextClick(view: View) {
            onNext(view as ImageView)
        }

        fun onListClick(view: View) {
            onList(view as ImageView)
        }
    }
}
