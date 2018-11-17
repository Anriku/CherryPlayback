package com.anriku.cherryplayback.network

/**
 * Created by anriku on 2018/11/17.
 */

object ImageUrl {

    fun getSingerImageUrl(singerId: Long, with: Int = 500) =
        "${BASE_IMAGE}music/photo/singer_${with}/${singerId % 100}/${with}_singerpic_${singerId}_0.jpg"


    fun getAlbumImageUrl(albumId: Long, with: Int = 500) =
        "${BASE_IMAGE}music/photo/album_${with}/${albumId % 100}/${with}_albumpic_${albumId}_0.jpg"

}