package com.anriku.cherryplayback.utils.extensions

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.anriku.cherryplayback.R

/**
 * Created by anriku on 2018/11/11.
 */


fun RecyclerView.setDivider() {
    ContextCompat.getDrawable(context, R.drawable.recycler_view_divider)?.let {
        addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                setDrawable(it)
            })
    }
}