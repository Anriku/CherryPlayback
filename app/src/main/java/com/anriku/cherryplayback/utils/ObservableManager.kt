package com.anriku.cherryplayback.utils

import io.reactivex.disposables.Disposable

/**
 * Created by anriku on 2018/11/10.
 */

class ObservableManager<T> {

    private val mHashMap: HashMap<T, Disposable> by lazy(LazyThreadSafetyMode.NONE) {
        HashMap<T, Disposable>()
    }

    fun put(key: T, disposable: Disposable) {
        mHashMap[key] = disposable
    }

    fun remove(key: T) {
        mHashMap.remove(key)
    }

    fun dispose(key: T) {
        val disposable = mHashMap[key]
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
        remove(key)
    }
}