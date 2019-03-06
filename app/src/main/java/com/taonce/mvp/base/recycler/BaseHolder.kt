package com.taonce.mvp.base.recycler

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.View


/**
 * Author: taoyongxiang
 * Date: 2018/12/4
 * Project: BaseAdapter
 * Desc: RecyclerView.ViewHolder基类
 */

class BaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

	private val mViews = SparseArrayCompat<View>()

	/**
	 * 通过resId获取view，通过as转换符将`V`转换成具体的`View`
	 * 具体请看`MainAdapter`的实现
	 */
	fun <V : View> getView(id: Int): V {
		var view = mViews[id]
		if (view == null) {
			view = itemView.findViewById(id)
			mViews.put(id, view)
		}
		return view as V
	}

}