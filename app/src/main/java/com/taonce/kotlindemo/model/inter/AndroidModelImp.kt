package com.taonce.kotlindemo.model.inter

import com.taonce.kotlindemo.bean.AndroidBean


/**
 * Author: taoyongxiang
 * Date: 2018/9/5
 * Project: KotlinDemo
 * Desc:
 */

interface AndroidModelImp {
    fun getAndroidData(listener: OnGetAndroidDataListener)

    interface OnGetAndroidDataListener {
        fun onGetAndroidDataFinished(bean: AndroidBean?)
    }
}