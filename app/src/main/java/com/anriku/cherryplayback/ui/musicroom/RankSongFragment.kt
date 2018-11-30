package com.anriku.cherryplayback.ui.musicroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.adapter.RankSongRecAdapter
import com.anriku.cherryplayback.databinding.FragmentRankSongBinding
import com.anriku.cherryplayback.network.ApiGenerate
import com.anriku.cherryplayback.network.QQMusicService
import com.anriku.cherryplayback.rxjava.ExecuteOnceObserver
import com.anriku.cherryplayback.ui.BaseFragment
import com.anriku.cherryplayback.utils.extensions.errorHandler
import com.anriku.cherryplayback.utils.extensions.setDivider
import com.anriku.cherryplayback.utils.extensions.setSchedulers
import com.anriku.cherryplayback.viewmodel.RankSongViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/**
 * Created by anriku on 2018/11/30.
 */

class RankSongFragment : BaseFragment() {

    private lateinit var mBinding: FragmentRankSongBinding
    private lateinit var mRankSongViewModel: RankSongViewModel
    private val mQQMusicService: QQMusicService by lazy(LazyThreadSafetyMode.NONE) {
        ApiGenerate.getGsonApiService(QQMusicService::class.java)
    }

    companion object {
        const val RANK_TYPE = "rank_type"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_rank_song, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFragment()
    }

    private fun initFragment() {
        activity ?: return
        setToolbar(mBinding.tb)

        val topId = arguments?.getString(RANK_TYPE) ?: return

        mRankSongViewModel =
                ViewModelProviders.of(this, RankSongViewModel.Factory(topId)).get(RankSongViewModel::class.java)

        val adapter = RankSongRecAdapter(activity!!)
        mBinding.rv.adapter = adapter
        mRankSongViewModel.rankSong.observe(this, Observer(adapter::submitList))
        mBinding.rv.setDivider()

        mQQMusicService
            .getRankSong(topId, 0, 1)
            .setSchedulers()
            .errorHandler()
            .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                Glide.with(mBinding.iv)
                    .load(it.topinfo.pic_v12)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(mBinding.iv)
                mBinding.tvUpdateTime.text = it.date
            }))
    }
}