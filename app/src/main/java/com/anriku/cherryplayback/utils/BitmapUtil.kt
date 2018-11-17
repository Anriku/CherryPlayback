package com.anriku.cherryplayback.utils

import android.os.Environment
import android.provider.MediaStore
import android.graphics.Bitmap
import android.content.Context
import android.graphics.BitmapFactory
import java.io.*


/**
 * Created by anriku on 2018/11/16.
 */

class BitmapUtil {

    fun storeBitmap(context: Context, bitmap: Bitmap, bitmapName: String = "dailyPic",
                    path: String = Environment.getExternalStorageDirectory().toString()) {
        val fOut: OutputStream?
        val file = File(path, "$bitmapName.jpg")
        fOut = FileOutputStream(file)

        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut)
        fOut.flush()
        fOut.close()

        MediaStore.Images.Media.insertImage(context.contentResolver, file.absolutePath, file.name, file.name)
    }


    fun getBitmap(bitmapName: String = "dailyPic",
                  path: String = Environment.getExternalStorageDirectory().toString()): Bitmap? {
        var bitmap: Bitmap? = null
        val file = File(path, "$bitmapName.jpg")
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.ARGB_8888
        try {
            bitmap = BitmapFactory.decodeStream(FileInputStream(file), null, options)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        return bitmap
    }
}