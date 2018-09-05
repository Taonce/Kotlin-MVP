package com.taonce.kotlindemo.view.inter

import com.taonce.kotlindemo.bean.AndroidBean


/**
 * Author: taoyongxiang
 * Date: 2018/9/5
 * Project: KotlinDemo
 * Desc:
 */

interface AndroidViewImp : BaseViewImp {
    fun showAndroidData(bean: AndroidBean)

    // 这里使用了默认参数，提示语一般都不变
    fun showErrorMsg(msg: String = "亲，网络出现故障咯")
}