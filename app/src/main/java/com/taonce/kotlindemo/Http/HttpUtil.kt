package com.taonce.kotlindemo.Http

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Author: taoyongxiang
 * Date: 2018/9/4
 * Project: KotlinDemo
 * Desc:
 */

class HttpUtil private constructor() {

    companion object {
        val mInstance: HttpUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            HttpUtil()
        }
    }


    fun <T> request(observable: Observable<T>, callBack: OnHttpResponse<T>) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<T> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onNext(value: T) {
                        callBack.onSuccess(value)
                    }

                    override fun onError(e: Throwable?) {
                        if (e != null) {
                            callBack.onFailed(e, e.message!!)
                        }
                    }

                })
    }
}