package com.anriku.cherryplayback.model

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity

/**
 * Created by anriku on 2018/10/31.
 */

@Entity(tableName = "songs", primaryKeys = ["id"])
class Song() : Parcelable {
    var id: Long = 0
    var data: String? = null
    var size: Long = 0
    var displayName: String? = null
    var title: String? = null
    var dateAdded: Long = 0
    var dateModified: Long = 0
    var mineType: String? = null
    var isDrm: Boolean? = null
    var duration: Long = 0
    var artist: String? = null
    var composer: String? = null
    var year: Int = 0
    var whetherMusic: Int = 0
    var albumId: Long = -1

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        data = parcel.readString()
        size = parcel.readLong()
        displayName = parcel.readString()
        title = parcel.readString()
        dateAdded = parcel.readLong()
        dateModified = parcel.readLong()
        mineType = parcel.readString()
        isDrm = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        duration = parcel.readLong()
        artist = parcel.readString()
        composer = parcel.readString()
        year = parcel.readInt()
        whetherMusic = parcel.readInt()
        albumId = parcel.readLong()
    }

    data class Album(
        var albumId: Long? = null,
        var album: String? = null,
        var firstYear: Int? = null,
        var lastYear: Int? = null,
        var albumArt: String? = null,
        var albumBitmap: Bitmap? = null
    )

    companion object CREATOR : Parcelable.Creator<Song> {
        override fun createFromParcel(parcel: Parcel): Song {
            return Song(parcel)
        }

        override fun newArray(size: Int): Array<Song?> {
            return arrayOfNulls(size)
        }
    }


    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeString(data)
        dest.writeLong(size)
        dest.writeString(displayName)
        dest.writeString(title)
        dest.writeLong(dateAdded)
        dest.writeLong(dateModified)
        dest.writeString(mineType)
        dest.writeValue(isDrm)
        dest.writeLong(duration)
        dest.writeString(artist)
        dest.writeString(composer)
        dest.writeInt(year)
        dest.writeInt(whetherMusic)
        dest.writeLong(albumId)
    }

    override fun describeContents(): Int = 0

    override fun toString(): String {
        return "Song(id=$id, data=$data, size=$size, displayName=$displayName, title=$title, " +
                "dateAdded=$dateAdded, dateModified=$dateModified, mineType=$mineType, " +
                "isDrm=$isDrm, duration=$duration, artist=$artist, composer=$composer, " +
                "year=$year, whetherMusic=$whetherMusic, albumId=$albumId)"
    }

}