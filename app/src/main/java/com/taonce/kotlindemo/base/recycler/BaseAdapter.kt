package com.taonce.kotlindemo.base.recycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup


/**
 * Author: taoyongxiang
 * Date: 2018/12/4
 * Project: BaseAdapter
 * Desc: RecyclerAdapter基类
 */

abstract class BaseAdapter<T>(private val ctx: Context, private val layoutRes: Int, val mData: MutableList<T>)
	: RecyclerView.Adapter<BaseHolder>() {

	// 利用闭包实现点击事件的lambda语法
	private var mItemClick: ((position: Int) -> Unit)? = null
	private var mItemLongClick: ((position: Int) -> Boolean)? = null

	fun setOnItemClickListener(itemClick: (position: Int) -> Unit) {
		this.mItemClick = itemClick
	}

	fun setOnItemLongClickListener(itemLongClick: (position: Int) -> Boolean) {
		this.mItemLongClick = itemLongClick
	}


	override fun onBindViewHolder(holder: BaseHolder, position: Int) {
		convert(holder, position)
		holder.itemView.setOnClickListener {
			// 这里一定要实现闭包的invoke()方法
			mItemClick?.invoke(position)
		}
		holder.itemView.setOnLongClickListener {
			if (mItemLongClick != null) {
				mItemLongClick!!.invoke(position)
			} else return@setOnLongClickListener false
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
		return BaseHolder(LayoutInflater.from(ctx).inflate(layoutRes, parent, false))
	}

	override fun getItemCount(): Int {
		return mData.size
	}

	/**
	 * 用来给具体Adapter实现逻辑的抽象方法
	 */
	abstract fun convert(holder: BaseHolder, position: Int)

	/**
	 * 添加一项数据
	 */
	fun addData(item: T) {
		mData.add(item)
		notifyDataSetChanged()
	}

	/**
	 * 添加数据
	 * [listData]：添加的数据
	 * [isDelete]：是否删除原来的数据
	 */
	fun addListData(listData: MutableList<T>, isDelete: Boolean) {
		if (isDelete) {
			mData.clear()
		}
		mData.addAll(listData)
		notifyDataSetChanged()
	}

	/**
	 * 删除指定项数据
	 * [position]:从0开始
	 */
	fun deletePositionData(position: Int) {
		// 防止position越界
		if (position > 0 && position < mData.size) {
			mData.remove(mData[position])
			notifyDataSetChanged()
		} else {
			Log.d("taonce", "delete item failed, position error!")
		}
	}

}