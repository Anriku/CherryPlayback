package com.anriku.cherryplayback.utils.extensions

import com.anriku.cherryplayback.network.DefaultErrorHandler
import com.anriku.cherryplayback.network.ErrorHandler
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by anriku on 2018/11/16.
 */

fun <T> Call<T>.simpleEnqueue(
    errorHandler: ErrorHandler = DefaultErrorHandler,
    onSimpleResponse: (Call<T>, Response<T>) -> Unit
) {
    enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            errorHandler.handle(t)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            onSimpleResponse(call, response)
        }

    })
}