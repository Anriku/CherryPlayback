package com.anriku.cherryplayback.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.databinding.FragmentMusicListBinding
import com.anriku.cherryplayback.viewmodel.SongsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.lang.IllegalStateException

/**
 * Created by anriku on 2018/10/31.
 */

class MusicListFragment : AppCompatDialogFragment() {

    private lateinit var mBottomSheetDialog: BottomSheetDialog
    private lateinit var mSongsViewModel: SongsViewModel
    private lateinit var mBinding: FragmentMusicListBinding

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity ?: throw IllegalStateException("MusicListFragment's attached activity is null!")

        mBottomSheetDialog = BottomSheetDialog(activity!!)
        mSongsViewModel = ViewModelProviders.of(activity!!).get(SongsViewModel::class.java)

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_music_list,
                null, false)

        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        mBottomSheetDialog.setContentView(mBinding.root, params)

        return mBottomSheetDialog
    }

}