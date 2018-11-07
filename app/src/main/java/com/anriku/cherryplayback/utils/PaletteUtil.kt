package com.anriku.cherryplayback.utils

import android.graphics.Bitmap
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.palette.graphics.Palette

/**
 * This class is used extract the color from a bitmap to let the toolbar's color reasonably change.
 *
 * Created by anriku on 2018/11/3.
 */
object PaletteUtil {

    fun createPalette(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()

    @ColorInt
    fun extractBackgroundColor(bitmap: Bitmap): Int {
        val lightMutedSwatch = createPalette(bitmap).lightMutedSwatch

        return lightMutedSwatch?.rgb ?: Color.parseColor("#cccccc")
    }

    @ColorInt
    fun extractTextColor(bitmap: Bitmap): Int {
        val lightMutedSwatch = createPalette(bitmap).lightMutedSwatch
        return lightMutedSwatch?.titleTextColor ?: Color.parseColor("444444")
    }
}