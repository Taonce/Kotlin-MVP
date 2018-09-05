package com.taonce.kotlindemo.presenter

import com.taonce.kotlindemo.bean.AndroidBean
import com.taonce.kotlindemo.model.AndroidModel
import com.taonce.kotlindemo.model.inter.AndroidModelImp
import com.taonce.kotlindemo.view.inter.AndroidViewImp


/**
 * Author: taoyongxiang
 * Date: 2018/9/5
 * Project: KotlinDemo
 * Desc:
 */

class AndroidPresenter(viewImp: AndroidViewImp) : BasePresenter(), AndroidModelImp.OnGetAndroidDataListener {

    private var androidModel: AndroidModelImp? = null
    private var viewImp: AndroidViewImp? = viewImp

    override fun detachView() {
        viewImp = null
    }

    fun getAndroidData() {
        viewImp!!.showLoading()
        androidModel!!.getAndroidData(this)
    }

    override fun onGetAndroidDataFinished(bean: AndroidBean?) {
        viewImp!!.hideLoading()
        if (bean != null) {
            viewImp!!.showAndroidData(bean)
            if (bean.error == "true") {
                viewImp!!.showErrorMsg()
            }
        } else {
            viewImp!!.showErrorMsg()
        }
    }

    init {
        androidModel = AndroidModel()
    }
}