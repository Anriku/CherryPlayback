package com.anriku.cherryplayback.utils.extensions

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.anriku.cherryplayback.R

/**
 * Created by anriku on 2018/11/2.
 */

private var mEditor: SharedPreferences.Editor? = null

fun Context.setSPValue(
    setValue: SharedPreferences.Editor.() -> Unit,
    spFileName: String = resources.getString(R.string.sp_file_name)
) {
    if (mEditor == null) {
        mEditor = getSPValue(spFileName).edit()
    }
    mEditor?.setValue()
    mEditor?.apply()
}

fun Context.getSPValue(spFileName: String = resources.getString(R.string.sp_file_name)): SharedPreferences {
    return getSharedPreferences(spFileName, MODE_PRIVATE)
}