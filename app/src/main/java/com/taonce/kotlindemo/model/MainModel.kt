package com.taonce.kotlindemo.model

import com.taonce.kotlindemo.http.RetrofitUtil
import com.taonce.kotlindemo.bean.AndroidBean
import com.taonce.kotlindemo.contract.IMainModel
import com.taonce.kotlindemo.http.BaseObserver
import com.taonce.kotlindemo.http.BaseService
import com.taonce.kotlindemo.http.RxSchedulers
import com.taonce.kotlindemo.util.LogUtil.showLog
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class MainModel : IMainModel {
    override fun getAndroidData(category: String, page: Int, listener: IMainModel.OnGetAndroidDataListener) {

        RetrofitUtil.mInstance.getService().getCategoryData(category, 10, page)
                .compose(RxSchedulers.observableTransformer())
                .subscribe(object : BaseObserver<AndroidBean> {
                    override fun onSuccess(value: AndroidBean) {
                        listener.onGetAndroidDataFinished(bean = value)
                    }

                    override fun onFailed() {
                        listener.onGetAndroidDataFinished(null)
                    }
                })
    }

}