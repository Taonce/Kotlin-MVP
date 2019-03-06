package com.taonce.kotlindemo.ui.demo

import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.taonce.kotlindemo.R
import com.taonce.kotlindemo.base.BaseMVPActivity
import com.taonce.kotlindemo.bean.AndroidBean
import com.taonce.kotlindemo.contract.IMainView
import com.taonce.kotlindemo.presenter.MainPresenter
import com.taonce.kotlindemo.util.LogUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainMVPActivity : BaseMVPActivity<IMainView, MainPresenter>(), IMainView {

	private val mainPresenter by lazy {
		getPresenter()
	}

	override fun getLayoutId(): Int {
		return R.layout.activity_main
	}

	override fun initData() {
		mainPresenter.getAndroidData("android", 1)
	}

	override fun initView() {
	}

	override fun initEvent() {
	}

	override fun getPresenter(): MainPresenter = MainPresenter(this)


	override fun showAndroidData(bean: AndroidBean) {
		LogUtil.showLog(msg = "$bean")
		rl_android.layoutManager = LinearLayoutManager(this)
		rl_android.adapter = MainAdapter(this, R.layout.item_android, bean.results)
	}

	override fun showErrorMsg(msg: String) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
	}


}