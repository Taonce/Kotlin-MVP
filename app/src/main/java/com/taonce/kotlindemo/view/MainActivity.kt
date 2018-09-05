package com.taonce.kotlindemo.view

import android.annotation.SuppressLint
import android.widget.Toast
import com.taonce.kotlindemo.Http.HttpUtil
import com.taonce.kotlindemo.Http.OnHttpResponse
import com.taonce.kotlindemo.Http.RetrofitUtil
import com.taonce.kotlindemo.R
import com.taonce.kotlindemo.base.BaseActivity
import com.taonce.kotlindemo.bean.AndroidBean
import com.taonce.kotlindemo.presenter.AndroidPresenter
import com.taonce.kotlindemo.presenter.BasePresenter
import com.taonce.kotlindemo.util.LogUtil
import com.taonce.kotlindemo.view.inter.AndroidViewImp
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), AndroidViewImp {

    private var androidPresenter: AndroidPresenter? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        androidPresenter = AndroidPresenter(this)
        androidPresenter!!.getAndroidData()
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
    }

    override fun initEvent() {

    }

    override fun bindPresenter(): BasePresenter {
        return androidPresenter!!
    }

    override fun showAndroidData(bean: AndroidBean) {
        tv_android_data.text = bean.toString()
    }

    override fun showErrorMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        androidPresenter!!.detachView()
    }
}
