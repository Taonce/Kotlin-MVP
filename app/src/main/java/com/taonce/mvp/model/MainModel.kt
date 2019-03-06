package com.taonce.mvp.model

import com.taonce.mvp.http.RetrofitUtil
import com.taonce.mvp.bean.AndroidBean
import com.taonce.mvp.contract.IMainModel
import com.taonce.mvp.http.BaseObserver
import com.taonce.mvp.http.BaseService
import com.taonce.mvp.http.RxSchedulers
import com.taonce.mvp.util.LogUtil.showLog
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