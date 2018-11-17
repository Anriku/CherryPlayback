package com.anriku.cherryplayback.adapter

import android.content.Context
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.model.SingerList
import com.anriku.cherryplayback.network.ApiGenerate
import com.anriku.cherryplayback.network.ImageUrl
import com.anriku.cherryplayback.network.QQMusicService
import com.anriku.cherryplayback.ui.SingerDetailFragment
import com.anriku.cherryplayback.utils.ObservableManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by anriku on 2018/11/8.
 */

class SingerListAdapter(private val mContext: Context) :
    BasePagedListAdapter<SingerList.DataBean.ListBean>(mContext, diffCallback) {

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

    override fun getThePositionLayoutId(position: Int): Int = R.layout.singer_list_rec_item

    override fun onViewRecycled(holder: BaseViewHolder) {
        val circleImageView = holder.itemView.findViewById<CircleImageView>(R.id.civ)
        Glide.with(circleImageView.context).clear(circleImageView)
        super.onViewRecycled(holder)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemView = holder.itemView.apply {
            tag = position
        }
        val item = getItem(position)

        itemView.findViewById<CircleImageView>(R.id.civ).apply {
            item?.fsinger_id?.let {
                Glide.with(this.context)
                    .load(ImageUrl.getSingerImageUrl(it.toLong(), 300))
                    .apply(RequestOptions().placeholder(R.drawable.ic_singer).error(R.drawable.ic_error))
                    .into(this)
            }
        }

        itemView.findViewById<TextView>(R.id.tv_name).apply {
            val name = item?.fsinger_name

            text = name
        }

        itemView.setOnClickListener {
            item?.let { singerInfo ->
                val bundle = bundleOf(SingerDetailFragment.SINGER_INFO to singerInfo)
                it.findNavController().navigate(R.id.action_singer_list_fragment_to_singer_detail_fragment, bundle)
            }

        }
    }
}