package com.anriku.cherryplayback.model

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
        var album: String? = null,
        var year: Int = 0,
        var isMusic: Int? = null)