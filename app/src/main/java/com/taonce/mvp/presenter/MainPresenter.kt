package com.taonce.mvp.presenter

import com.taonce.mvp.base.BasePresenter
import com.taonce.mvp.bean.AndroidBean
import com.taonce.mvp.model.MainModel
import com.taonce.mvp.contract.IMainModel
import com.taonce.mvp.contract.IMainView

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