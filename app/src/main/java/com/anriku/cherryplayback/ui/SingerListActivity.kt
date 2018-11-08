package com.anriku.cherryplayback.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.adapter.SingerListAdapter
import com.anriku.cherryplayback.databinding.ActivitySingerListBinding
import com.anriku.cherryplayback.viewmodel.SingerListViewModel

class SingerListActivity : BaseActivity() {

    private lateinit var mBinding: ActivitySingerListBinding
    private lateinit var mSingerListViewModel: SingerListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_singer_list)

        mSingerListViewModel = ViewModelProviders.of(this).get(SingerListViewModel::class.java)
        val adapter = SingerListAdapter(this)
        mBinding.rv.adapter = adapter
        mSingerListViewModel.singerList.observe(this, Observer(adapter::submitList))

    }
}
