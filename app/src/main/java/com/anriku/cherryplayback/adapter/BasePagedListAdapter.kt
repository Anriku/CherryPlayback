package com.anriku.cherryplayback.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by anriku on 2018/11/9.
 */

abstract class BasePagedListAdapter<T>(
    private val mContext: Context, diffCallback: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, BasePagedListAdapter.BaseViewHolder>(diffCallback) {

    protected val inflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasePagedListAdapter.BaseViewHolder {
        return BasePagedListAdapter.BaseViewHolder(inflater.inflate(viewType, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return getThePositionLayoutId(position)
    }

    /**
     * @return 返回对应位置View的LayoutId
     */
    @LayoutRes
    abstract fun getThePositionLayoutId(position: Int): Int

    abstract override fun onBindViewHolder(
        holder: BasePagedListAdapter.BaseViewHolder,
        position: Int
    )

    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}