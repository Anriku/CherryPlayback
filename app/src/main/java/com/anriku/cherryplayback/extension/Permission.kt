package com.anriku.cherryplayback.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.anriku.cherryplayback.rxjava.ExecuteOnceObserver
import com.tbruyelle.rxpermissions2.RxPermissions

/**
 * Created by anriku on 2018/11/14.
 */

fun FragmentActivity.request(
    vararg permissions: String, onGrant: () -> Unit,
    onReject: () -> Unit
) {
    val rxPermissions = RxPermissions(this)
    rxPermissions.request(*permissions)
        .subscribe(ExecuteOnceObserver(
            onExecuteOnceNext = {
                if (it) {
                    onGrant()
                } else {
                    onReject()
                }
            }
        ))
}


fun Fragment.request(
    vararg permissions: String, onGrant: () -> Unit,
    onReject: () -> Unit
) {
    val rxPermissions = RxPermissions(this)
    rxPermissions.request(*permissions)
        .subscribe(ExecuteOnceObserver(
            onExecuteOnceNext = {
                if (it) {
                    onGrant()
                } else {
                    onReject()
                }
            }
        ))
}