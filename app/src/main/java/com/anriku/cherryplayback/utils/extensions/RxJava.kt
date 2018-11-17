package com.anriku.cherryplayback.utils.extensions

import com.anriku.cherryplayback.network.DefaultErrorHandler
import com.anriku.cherryplayback.network.ErrorHandler
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by anriku on 2018/11/8.
 */

/**
 * note：请放在有UI操作的操作符前（map等操作符后）, 否则将抛出异常，原因：{@see <a href="https://www.jianshu.com/p/3e5d53e891db"/>}
 */
fun <T> Observable<T>.setSchedulers(
    subscribeOn: Scheduler = Schedulers.io(),
    unsubscribeOn: Scheduler = Schedulers.io(),
    observeOn: Scheduler = AndroidSchedulers.mainThread()
): Observable<T> = subscribeOn(subscribeOn)
    .unsubscribeOn(unsubscribeOn)
    .observeOn(observeOn)

fun <T> Observable<T>.errorHandler(errorHandler: ErrorHandler = DefaultErrorHandler) = doOnError { errorHandler.handle(it) }
