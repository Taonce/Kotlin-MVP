package com.taonce.kotlindemo.model

import com.taonce.kotlindemo.Http.HttpUtil
import com.taonce.kotlindemo.Http.OnHttpResponse
import com.taonce.kotlindemo.Http.RetrofitUtil
import com.taonce.kotlindemo.bean.AndroidBean
import com.taonce.kotlindemo.contract.IMainModel
import com.taonce.kotlindemo.util.LogUtil
import io.reactivex.Observable

class MainModel : IMainModel {
    override fun getAndroidData(category: String, page: Int, listener: IMainModel.OnGetAndroidDataListener) {
        val observable: Observable<AndroidBean> = RetrofitUtil.mInstance.getService().getCategoryData(category, 10, page)
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