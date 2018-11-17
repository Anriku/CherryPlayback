package com.anriku.cherryplayback.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.adapter.SingerListAdapter
import com.anriku.cherryplayback.databinding.FragmentSingerListBinding
import com.anriku.cherryplayback.utils.extensions.setDivider
import com.anriku.cherryplayback.viewmodel.SingerListViewModel
import kotlinx.android.synthetic.main.base_toolbar.*

class SingerListFragment : BaseFragment() {

    private lateinit var mBinding: FragmentSingerListBinding
    private lateinit var mSingerListViewModel: SingerListViewModel
    private var startOffset: Int? = null

    companion object {
        private const val TAG = "SingerListFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_singer_list, container, false)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFragment()
    }

    private fun initFragment() {
        val appComActivity: AppCompatActivity = activity as? AppCompatActivity ?: return

        mSingerListViewModel = ViewModelProviders.of(this).get(SingerListViewModel::class.java)

        appComActivity.setSupportActionBar(tb)
        appComActivity.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        mBinding.rv.setDivider()
        tb.findViewById<TextView>(R.id.title).apply {
            text = "歌手列表"
        }

        val adapter = SingerListAdapter(context!!)
        mBinding.rv.adapter = adapter
        mSingerListViewModel.singerList.observe(this, Observer(adapter::submitList))
    }

}
