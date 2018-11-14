package com.anriku.cherryplayback.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.adapter.SongListRecAdapter
import com.anriku.cherryplayback.databinding.FragmentMusicListBinding
import com.anriku.cherryplayback.extension.setDivider
import com.anriku.cherryplayback.viewmodel.SongsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.jetbrains.anko.dip
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

        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.fragment_music_list,
            null, false
        )

        mBinding.listeners = ClickListeners(onClose = {
            dismiss()
        })

        mBinding.adapter = SongListRecAdapter(activity!!)
        mBinding.rv.setDivider()

        mBottomSheetDialog = BottomSheetDialog(activity!!)
        val params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            activity!!.dip(400)
        )
        mBottomSheetDialog.setContentView(mBinding.root, params)

        mSongsViewModel = ViewModelProviders.of(activity!!).get(SongsViewModel::class.java)

        return mBottomSheetDialog
    }


    class ClickListeners(val onClose: (TextView) -> Unit) {

        fun onCloseClick(view: View) {
            onClose(view as TextView)
        }

    }

}