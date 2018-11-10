package com.anriku.cherryplayback.network

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

/**
 * Created by anriku on 2018/11/10.
 */

fun <T> Observable<T>.subscribeWithDispose(
    onNext: (T) -> Unit,
    onError: (Throwable) -> Unit = {},
    onComplete: () -> Unit = {},
    onSubscribe: (Disposable) -> Unit = {}
): Disposable = subscribe(onNext, onError, onComplete, onSubscribe)