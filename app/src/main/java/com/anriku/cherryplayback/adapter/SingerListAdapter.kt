package com.anriku.cherryplayback.adapter

import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.extension.errorHandler
import com.anriku.cherryplayback.extension.setSchedulers
import com.anriku.cherryplayback.model.SingerList
import com.anriku.cherryplayback.network.ApiGenerate
import com.anriku.cherryplayback.network.QQMusicService
import com.anriku.cherryplayback.network.subscribeWithDispose
import com.anriku.cherryplayback.rxjava.ExecuteOnceObserver
import com.anriku.cherryplayback.ui.SingerDetailActivity
import com.anriku.cherryplayback.utils.LogUtil
import com.anriku.cherryplayback.utils.ObservableManager
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.compiler.GlideIndexer_GlideExtension_com_anriku_cherryplayback_extension_MyGlideExtension
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

    private val mQQMusicService: QQMusicService by lazy(LazyThreadSafetyMode.NONE) {
        ApiGenerate.getApiService(QQMusicService::class.java)
    }
    private val mObservableManager: ObservableManager<Int> by lazy(LazyThreadSafetyMode.NONE) {
        ObservableManager<Int>()
    }

    override fun getThePositionLayoutId(position: Int): Int = R.layout.singer_list_rec_item

    override fun onViewRecycled(holder: BaseViewHolder) {
        mObservableManager.dispose(holder.itemView.tag as Int)
        Glide.with(holder.itemView).clear(holder.itemView.findViewById<CircleImageView>(R.id.civ))
        super.onViewRecycled(holder)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemView = holder.itemView.apply {
            tag = position
        }
        val item = getItem(position)

        itemView.findViewById<CircleImageView>(R.id.civ).apply {
            item?.fsinger_name?.let {

                val enBracket = it.indexOf('(')
                val zhBracket = it.indexOf('（')
                val name = when {
                    enBracket != -1 -> it.substring(0, enBracket)
                    zhBracket != -1 -> it.substring(0, zhBracket)
                    else -> it
                }

                val disposable = mQQMusicService.search(name, 10, 1)
                    .setSchedulers()
                    .errorHandler()
                    .subscribeWithDispose(onNext = { searchResult ->
                        Glide.with(this.context)
                            .load(searchResult.data.zhida.zhida_singer.singerPic)
                            .apply(RequestOptions().placeholder(R.drawable.ic_singer).error(R.drawable.ic_error))
                            .into(this)
                    })
                mObservableManager.put(itemView.tag as Int, disposable)
            }
        }

        itemView.findViewById<TextView>(R.id.tv_name).apply {
            val name = item?.fsinger_name

            text = name
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