package com.anriku.cherryplayback.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.adapter.SingerListAdapter
import com.anriku.cherryplayback.databinding.ActivitySingerListBinding
import com.anriku.cherryplayback.extension.setDivider
import com.anriku.cherryplayback.viewmodel.SingerListViewModel
import com.google.android.material.appbar.AppBarLayout

class SingerListActivity : BaseActivity() {

    private lateinit var mBinding: ActivitySingerListBinding
    private lateinit var mSingerListViewModel: SingerListViewModel
    private var startOffset: Int? = null
    companion object {
        private const val TAG = "SingerListActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_singer_list)

        initActivity()
    }

    private fun initActivity() {
        mSingerListViewModel = ViewModelProviders.of(this).get(SingerListViewModel::class.java)
        setSupportActionBar(mBinding.tb as Toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        mBinding.rv.setDivider()
        mBinding.tb.findViewById<TextView>(R.id.title).apply {
            text = "歌手列表"
        }

        val adapter = SingerListAdapter(this)
        mBinding.rv.adapter = adapter
        mSingerListViewModel.singerList.observe(this, Observer(adapter::submitList))
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
