package com.anriku.cherryplayback.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.model.SingerList
import com.anriku.cherryplayback.network.ImageUrl
import com.anriku.cherryplayback.ui.SingerDetailFragment
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
        val circleImageView = holder.itemView.findViewById<CircleImageView>(R.id.civ_singer)
        Glide.with(circleImageView.context).clear(circleImageView)
        super.onViewRecycled(holder)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemView = holder.itemView.apply {
            tag = position
        }
        val item = getItem(position)

        val civSinger = itemView.findViewById<CircleImageView>(R.id.civ_singer).apply {
            item?.fsinger_id?.let {
                Glide.with(this.context)
                        .load(ImageUrl.getSingerImageUrl(it.toLong(), 300))
                        .apply(RequestOptions().placeholder(R.drawable.ic_singer)
                                .error(R.drawable.ic_error))
                        .into(this)
            }
        }

        itemView.findViewById<TextView>(R.id.tv_name).apply {
            val name = item?.fsinger_name

            text = name
        }

        itemView.setOnClickListener {
            item?.let { singerInfo ->
                val transitionName = "iv_singer$position"
                ViewCompat.setTransitionName(civSinger, transitionName)
                val extras = FragmentNavigatorExtras(civSinger to transitionName)
                val bundle = bundleOf(SingerDetailFragment.SINGER_INFO to singerInfo,
                        SingerDetailFragment.TRANSITION_NAME to transitionName)
                it.findNavController().navigate(
                        R.id.action_singer_list_fragment_to_singer_detail_fragment,
                        bundle, null, extras
                )
            }

        }
    }
}