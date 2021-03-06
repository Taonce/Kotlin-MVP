package com.taonce.mvp.ui.demo

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.taonce.mvp.R
import com.taonce.mvp.base.BaseMVPActivity
import com.taonce.mvp.bean.AndroidBean
import com.taonce.mvp.contract.IMainView
import com.taonce.mvp.presenter.MainPresenter
import com.taonce.mvp.util.LogUtil
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
		rl_android.layoutManager = LinearLayoutManager(this@MainMVPActivity)
		val adapter = MainAdapter(this, R.layout.item_android, bean.results)
		rl_android.adapter = adapter
		adapter.setOnItemClickListener {
			LogUtil.showLog(msg = "Click position is $it")
		}
		adapter.setOnItemLongClickListener {
			LogUtil.showLog(msg = "Long Click position is $it")
			return@setOnItemLongClickListener true
		}
	}

	override fun showErrorMsg(msg: String) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
	}


}