package com.taonce.mvp.ui.demo

import android.content.Context
import android.graphics.Color
import android.widget.TextView
import com.taonce.mvp.R
import com.taonce.mvp.base.recycler.BaseAdapter
import com.taonce.mvp.base.recycler.BaseHolder
import com.taonce.mvp.bean.AndroidBean


/**
 * Author: taoyongxiang
 * Date: 2019/3/6
 * Project: Kotlin-RxJava-Retrofit
 * Desc:
 */

class MainAdapter(ctx: Context, layoutRes: Int, mData: MutableList<AndroidBean.Results>)
	: BaseAdapter<AndroidBean.Results>(ctx, layoutRes, mData) {
	override fun convert(holder: BaseHolder, position: Int) {
		val title = holder.getView<TextView>(com.taonce.mvp.R.id.item_android_title)
		title.apply {
			text = mData[position].desc
			setTextColor(Color.RED)
		}
		holder.getView<TextView>(R.id.item_android_author).apply {
			text = mData[position].who
			setTextColor(Color.BLACK)
		}
		holder.getView<TextView>(R.id.item_android_time).apply {
			text = mData[position].publishedAt
		}
	}
}