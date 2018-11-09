package com.anriku.cherryplayback.adapter

import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.model.SingerDetail
import java.lang.StringBuilder
import kotlin.math.sin

/**
 * Created by anriku on 2018/11/9.
 */

class SingerDetailAdapter(private val mContext: Context) :
    BasePagedListAdapter<SingerDetail.DataBean.ListBean>(mContext, diffCallback) {

    companion object {
        private const val TAG = "SingerDetailAdapter"

        val diffCallback = object : DiffUtil.ItemCallback<SingerDetail.DataBean.ListBean>() {
            override fun areItemsTheSame(
                oldItem: SingerDetail.DataBean.ListBean,
                newItem: SingerDetail.DataBean.ListBean
            ): Boolean {
                return oldItem.vid == newItem.vid
            }

            override fun areContentsTheSame(
                oldItem: SingerDetail.DataBean.ListBean,
                newItem: SingerDetail.DataBean.ListBean
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun getThePositionLayoutId(position: Int): Int =
        R.layout.singer_detail_rec_item

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemView = holder.itemView
        val item = getItem(position)


        itemView.findViewById<TextView>(R.id.tv_song).apply {
            text = item?.musicData?.songname
        }

        itemView.findViewById<TextView>(R.id.tv_artist).apply {
            val singers = item?.musicData?.singer ?: return

            val artistBuilder = StringBuilder()
            for (singer in singers) {
                artistBuilder.append(singer.name)
            }

            text = artistBuilder.toString()
        }
    }
}