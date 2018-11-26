package com.anriku.cherryplayback.network

import com.anriku.cherryplayback.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by anriku on 2018/11/8.
 */

object ApiGenerate {

    private const val DEFAULT_TIME_OUT = 30

    private lateinit var mRetrofit: Retrofit
    private lateinit var mOkHttpClient: OkHttpClient


    private fun getGsonRetrofit(baseUrl: String): Retrofit {
        mOkHttpClient = configureOkHttp(OkHttpClient.Builder())
        mRetrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return mRetrofit
    }

    private fun getXMLRetrofit(baseUrl: String): Retrofit {
        mOkHttpClient = configureOkHttp(OkHttpClient.Builder())
        mRetrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(mOkHttpClient)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return mRetrofit
    }

    private fun configureOkHttp(builder: OkHttpClient.Builder): OkHttpClient {
        builder.connectTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)
        }
        return builder.build()
    }

    fun <T> getXMLApiService(clazz: Class<T>, baseUrl: String = BASE_MUSIC) = getXMLRetrofit(baseUrl).create(clazz)

    fun <T> getGsonApiService(clazz: Class<T>, baseUrl: String = BASE_MUSIC) = getGsonRetrofit(baseUrl).create(clazz)

    fun <T> getApiService(retrofit: Retrofit, clazz: Class<T>) = retrofit.create(clazz)
}