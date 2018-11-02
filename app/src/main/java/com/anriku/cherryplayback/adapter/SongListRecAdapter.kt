package com.anriku.cherryplayback.adapter

import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.utils.LogUtil
import com.anriku.cherryplayback.viewmodel.SongsViewModel

/**
 * Created by anriku on 2018/11/1.
 */

class SongListRecAdapter(private val mActivity: FragmentActivity) : BaseRecAdapter(mActivity) {

    companion object {
        private const val TAG = "SongListRecAdapter"
    }

    private val mSongsViewModel: SongsViewModel = ViewModelProviders.of(mActivity).get(SongsViewModel::class.java)

    override fun getThePositionLayoutId(position: Int): Int {
        return R.layout.music_list_rec_item
    }

    override fun getItemCount(): Int {
        return mSongsViewModel.binder?.getSongs()?.size ?: 0
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val songs = mSongsViewModel.binder?.getSongs() ?: mutableListOf()

        holder.itemView.findViewById<TextView>(R.id.song).apply {
            LogUtil.d(TAG, songs[position].title.toString())
            text = songs[position].title.toString()
        }
        holder.itemView.findViewById<TextView>(R.id.artist).apply {
            text = songs[position].artist.toString()
        }

        holder.itemView.setOnClickListener {
            mSongsViewModel.binder?.loadLocalMedia(position)
            mSongsViewModel.binder?.play()
        }
    }
}