package com.taonce.kotlindemo.util

import android.util.Log


/**
 * Author: taoyongxiang
 * Date: 2018/9/4
 * Project: KotlinDemo
 * Desc: 日志工具类
 */

object LogUtil {

    fun showLog(tag: String = "taonce", msg: String) {
        Log.d(tag, msg)
    }
}