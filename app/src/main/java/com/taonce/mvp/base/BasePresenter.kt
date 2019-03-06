package com.taonce.mvp.base

open class BasePresenter<V> {

    private var view: V? = null

    fun attachView(view: V) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun isAttach(): Boolean {
        return view != null
    }

}