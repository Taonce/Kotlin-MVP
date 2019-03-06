package com.taonce.mvp.contract

import com.taonce.mvp.bean.AndroidBean
import com.taonce.mvp.ui.inter.IBaseView

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
