package com.anriku.cherryplayback.utils

import android.graphics.Bitmap
import androidx.palette.graphics.Palette

/**
 * This class is used extract the color from a bitmap to let the toolbar's color reasonably change.
 *
 * Created by anriku on 2018/11/3.
 */
object PaletteUtil {

    var backgroundColor: Int? = null
    var textColor: Int? = null

    /**
     * Create a [Palette] using a bitmap.
     *
     * @param bitmap
     */
    fun createPalette(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()

    /**
     * Extract the bitmap's color
     *
     * @param bitmap
     */
    fun extractColors(bitmap: Bitmap) {
        val vibrantSwatch = createPalette(bitmap).vibrantSwatch

        backgroundColor = vibrantSwatch?.rgb
        textColor = vibrantSwatch?.titleTextColor
    }
}