package com.anriku.cherryplayback.model

/**
 * Created by anriku on 2018/11/8.
 */

open class SingerWrapper <T>(
    var code: Int = 0,
    var data: T? = null,
    var message: String? = null,
    var subcode: Int = 0
)