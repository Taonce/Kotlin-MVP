package com.taonce.kotlindemo.model

import com.taonce.kotlindemo.Http.HttpUtil
import com.taonce.kotlindemo.Http.OnHttpResponse
import com.taonce.kotlindemo.Http.RetrofitUtil
import com.taonce.kotlindemo.bean.AndroidBean
import com.taonce.kotlindemo.model.inter.AndroidModelImp
import com.taonce.kotlindemo.util.LogUtil
import io.reactivex.Observable


/**
 * Author: taoyongxiang
 * Date: 2018/9/5
 * Project: KotlinDemo
 * Desc:
 */

class AndroidModel : AndroidModelImp {

    override fun getAndroidData(listener: AndroidModelImp.OnGetAndroidDataListener) {
        val observable: Observable<AndroidBean> = RetrofitUtil.mInstance.getService().getCategoryData("android", 12, 1)
        HttpUtil.mInstance.request(observable, object : OnHttpResponse<AndroidBean> {
            override fun onSuccess(bean: AndroidBean) {
                listener.onGetAndroidDataFinished(bean)
            }

            override fun onFailed(t: Throwable, msg: String) {
                listener.onGetAndroidDataFinished(null)
                LogUtil.showLog(msg = msg)
            }
        })
    }

}