package com.taonce.mvp.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.taonce.mvp.R
import com.taonce.mvp.ui.inter.IBaseView

abstract class BaseMVPFragment : Fragment(), IBaseView {

    private var baseLoadingView: BaseLoadingView? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (getLayoutId() != 0) {
            inflater?.inflate(getLayoutId(), container, false)
        } else {
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initEvent()
    }

    abstract fun getLayoutId(): Int

    abstract fun initData()

    abstract fun initView()

    abstract fun initEvent()

    private fun initLoadingView() {
        baseLoadingView = BaseLoadingView(this.context, R.style.transparent_dialog)
    }

    private fun showLoadingView() {
        if (null != baseLoadingView) {
            baseLoadingView!!.show()
        } else {
            initLoadingView()
            baseLoadingView!!.show()
        }
    }

    private fun hideLoadingView() {
        if (null != baseLoadingView && baseLoadingView!!.isShowing) {
            baseLoadingView!!.cancel()
        }
    }

    /**
     * 将显示dialog和取消dialog放在了基础类中
     */
    override fun showLoading() {
        showLoadingView()
    }

    override fun hideLoading() {
        hideLoadingView()
    }

    override fun onDestroy() {
        super.onDestroy()
        super.onDestroy()

        // 销毁dialog
        if (null != baseLoadingView && baseLoadingView!!.isShowing) {
            baseLoadingView!!.dismiss()
        }
        baseLoadingView = null
    }
}