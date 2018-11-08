package com.anriku.cherryplayback.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.model.SingerList
import com.anriku.cherryplayback.network.ApiGenerate
import com.anriku.cherryplayback.network.QQMusicService
import com.anriku.cherryplayback.ui.SingerDetailActivity
import com.anriku.cherryplayback.utils.LogUtil

/**
 * Created by anriku on 2018/11/8.
 */

class SingerListAdapter(private val mContext: Context) :
    PagedListAdapter<SingerList.DataBean.ListBean, SingerListAdapter.ViewHolder>(diffCallback) {

    companion object {
        private const val TAG = "SingerListAdapter"

        val diffCallback = object : DiffUtil.ItemCallback<SingerList.DataBean.ListBean>() {
            override fun areItemsTheSame(
                oldItem: SingerList.DataBean.ListBean,
                newItem: SingerList.DataBean.ListBean
            ): Boolean {
                return oldItem.fsinger_id == newItem.fsinger_id
            }

            override fun areContentsTheSame(
                oldItem: SingerList.DataBean.ListBean,
                newItem: SingerList.DataBean.ListBean
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    private val mService: QQMusicService by lazy(LazyThreadSafetyMode.NONE) {
        ApiGenerate.getApiService(QQMusicService::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.singer_list_rec_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(getItem(position))
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindViewHolder(item: SingerList.DataBean.ListBean?) {
            itemView.findViewById<TextView>(R.id.other_name).apply {
                text = item?.fother_name
            }
            itemView.findViewById<TextView>(R.id.name).apply {
                LogUtil.d(TAG, item?.fsinger_name.toString())
                text = item?.fsinger_name
            }

            itemView.setOnClickListener {
                itemView.context.startActivity(
                    Intent(itemView.context, SingerDetailActivity::class.java).apply {
                        putExtra(SingerDetailActivity.SINGER_INFO, item)
                    }
                )
            }
        }
    }
}