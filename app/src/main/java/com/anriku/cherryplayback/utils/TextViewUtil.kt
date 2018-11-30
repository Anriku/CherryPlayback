package com.anriku.cherryplayback.utils

import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 * Created by anriku on 2018/11/30.
 */

object TextViewUtil {

    fun setTextView(textView: TextView, @DrawableRes drawableRes: Int, string: String = "") {
        val drawable = ContextCompat.getDrawable(textView.context, drawableRes)?.apply {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        }

        textView.apply {
            setCompoundDrawables(drawable, null, null, null)
            text = string
        }
    }

    fun setTextView(textView: TextView, drawable: Drawable?, string: String = "") {

        textView.apply {
            setCompoundDrawables(drawable, null, null, null)
            text = string
        }
    }

}