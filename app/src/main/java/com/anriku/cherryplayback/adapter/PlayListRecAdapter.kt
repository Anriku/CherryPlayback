package com.anriku.cherryplayback.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.model.RecommendPlaylist
import com.anriku.cherryplayback.ui.musicsquare.PlayListDetailFragment
import com.bumptech.glide.Glide

/**
 * Created by anriku on 2018/11/18.
 */

class PlayListRecAdapter(context: Context, private val mRecommendPlaylist: List<RecommendPlaylist.RecomPlaylistBean.DataBean.VHotBean>)
    : BaseRecAdapter(context) {


    override fun getThePositionLayoutId(position: Int): Int {
        return if (position == 0) {
            R.layout.recommend_play_list_header_rec_item
        } else {
            R.layout.recommend_play_list_rec_item
        }
    }

    override fun getItemCount(): Int {
        return mRecommendPlaylist.size + 1
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (position > 0) {
            val itemView = holder.itemView
            val playlist = mRecommendPlaylist[position - 1]

            val imageView = itemView.findViewById<ImageView>(R.id.iv)
            Glide.with(imageView).load(playlist.cover).into(imageView)

            itemView.findViewById<TextView>(R.id.tv_listen).apply {
                text = playlist.listen_num.toString()
            }

            itemView.findViewById<TextView>(R.id.tv_title).apply {
                text = playlist.title
            }

            itemView.findViewById<TextView>(R.id.tv_user_name).apply {
                text = playlist.username
            }

            itemView.setOnClickListener {
                val playlistId = bundleOf(PlayListDetailFragment.PLAYLIST_ID to playlist.content_id.toString())
                it.findNavController().navigate(
                        R.id.action_main_container_fragment_to_playListDetailFragment
                        , playlistId)
            }
        }
    }
}