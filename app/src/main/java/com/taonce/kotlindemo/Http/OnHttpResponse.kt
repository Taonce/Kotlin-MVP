package com.taonce.kotlindemo.Http


/**
 * Author: taoyongxiang
 * Date: 2018/9/4
 * Project: KotlinDemo
 * Desc:
 */

interface OnHttpResponse<T> {
    fun onSuccess(bean: T)
    fun onFailed(t: Throwable, msg: String)
}