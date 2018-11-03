package com.anriku.cherryplayback.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory

/**
 * Created by anriku on 2018/10/31.
 */

data class Song(
    var id: Long = 0,
    var data: String? = null,
    var size: Long = 0,
    var displayName: String? = null,
    var title: String? = null,
    var dateAdded: Long = 0,
    var dateModified: Long = 0,
    var mineType: String? = null,
    var isDrm: Boolean? = null,
    var duration: Long = 0,
    var artist: String? = null,
    var composer: String? = null,
    var year: Int = 0,
    var isMusic: Int? = null,
    var albumId: Long? = null
) {

    class Album(
        var albumId: Long? = null,
        var album: String? = null,
        var firstYear: Int? = null,
        var lastYear: Int? = null,
        var albumArt: String? = null,
        var albumBitmap: Bitmap? = null
    )
}