package com.taonce.kotlindemo.contract

import com.taonce.kotlindemo.bean.AndroidBean
import com.taonce.kotlindemo.ui.inter.IBaseView

interface IMainModel {
    fun getAndroidData(category: String, page: Int, listener: OnGetAndroidDataListener)

    interface OnGetAndroidDataListener {
        fun onGetAndroidDataFinished(bean: AndroidBean?)
    }
}

interface IMainView : IBaseView {
    fun showAndroidData(bean: AndroidBean)

    fun showErrorMsg(msg: String = "亲，网络出现问题了哦~")
}
