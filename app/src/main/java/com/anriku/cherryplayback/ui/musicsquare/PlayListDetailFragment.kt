package com.anriku.cherryplayback.ui.musicsquare

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.adapter.PlaylistDetailRecAdapter
import com.anriku.cherryplayback.databinding.FragmentPlaylistDetailBinding
import com.anriku.cherryplayback.ui.BaseFragment
import com.anriku.cherryplayback.utils.extensions.setDivider
import com.anriku.cherryplayback.viewmodel.SquareViewModel
import com.bumptech.glide.Glide

/**
 * Created by anriku on 2018/11/18.
 */

class PlayListDetailFragment : BaseFragment() {


    companion object {
        const val TAG = "PlayListDetailFragment"
        const val PLAYLIST_ID = "playlist_id"
    }

    private lateinit var mBinding: FragmentPlaylistDetailBinding
    private lateinit var mSquareViewModel: SquareViewModel
    private lateinit var mPlaylistId: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_playlist_detail, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity ?: return
        setToolbar(mBinding.abl.findViewById(R.id.tb))

        mPlaylistId = arguments?.getString(PLAYLIST_ID) ?: return
        mSquareViewModel = ViewModelProviders.of(activity!!).get(SquareViewModel::class.java)
        mSquareViewModel.getPlaylistDetail(mPlaylistId) {
            Glide.with(mBinding.ivLogo).load(it.cdlist[0].logo).into(mBinding.ivLogo)
            mBinding.tvUserName.text = it.cdlist[0].nickname
            mBinding.tvTitle.text = it.cdlist[0].dissname
            mBinding.tvBrief.text = it.cdlist[0].desc

            mBinding.rv.adapter = PlaylistDetailRecAdapter(activity!!, it.cdlist[0].songlist)
            mBinding.rv.setDivider()
        }
    }

}