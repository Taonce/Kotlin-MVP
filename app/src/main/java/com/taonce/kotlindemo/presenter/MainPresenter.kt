package com.taonce.kotlindemo.presenter

import com.taonce.kotlindemo.base.BasePresenter
import com.taonce.kotlindemo.bean.AndroidBean
import com.taonce.kotlindemo.model.MainModel
import com.taonce.kotlindemo.contract.IMainModel
import com.taonce.kotlindemo.contract.IMainView

class MainPresenter(private var mView: IMainView) : BasePresenter<IMainView>(), IMainModel.OnGetAndroidDataListener {

    private var mModel: IMainModel? = null

    init {
        mModel = MainModel()
    }

    fun getAndroidData(category: String, page: Int) {
        this.mView.showLoading()
        this.mModel?.getAndroidData(category, page, this)
    }

    override fun onGetAndroidDataFinished(bean: AndroidBean?) {
        mView.hideLoading()

        bean?.let {
            mView.showAndroidData(it)
        }
    }

}