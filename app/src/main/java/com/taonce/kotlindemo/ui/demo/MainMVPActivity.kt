package com.taonce.kotlindemo.ui.demo

import android.widget.Toast
import com.taonce.kotlindemo.R
import com.taonce.kotlindemo.base.BaseMVPActivity
import com.taonce.kotlindemo.bean.AndroidBean
import com.taonce.kotlindemo.contract.IMainView
import com.taonce.kotlindemo.presenter.MainPresenter
import com.taonce.kotlindemo.util.LogUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainMVPActivity : BaseMVPActivity<IMainView, MainPresenter>(), IMainView {

    private var mainPresenter: MainPresenter? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        mainPresenter?.getAndroidData("android", 1)
    }

    override fun initView() {
    }

    override fun initEvent() {
    }

    override fun getPresenter(): MainPresenter {
        mainPresenter = MainPresenter(this)
        return mainPresenter!!
    }


    override fun showAndroidData(bean: AndroidBean) {
        LogUtil.showLog(msg = "$bean")
        tv_android_data.text = bean.toString()
    }

    override fun showErrorMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}