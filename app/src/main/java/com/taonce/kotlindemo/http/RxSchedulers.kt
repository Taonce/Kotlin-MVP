package com.taonce.kotlindemo.http

import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable
import io.reactivex.Flowable


/**
 * Author: taoyongxiang
 * Date: 2019/1/24
 * Project: Kotlin-RxJava-Retrofit-master
 * Desc: 对RxJava的线程调度进行封装
 */

object RxSchedulers {

	/**
	 * [Observable] 线程切换
	 * [observableScheduler] 是被观察者所在的线程,默认为 [Schedulers.io]
	 * [observerScheduler] 是观察者所在的线程,默认为 [AndroidSchedulers.mainThread]
	 * return [ObservableTransformer]
	 */
	fun <T> observableTransformer(observableScheduler: Scheduler = Schedulers.io(), observerScheduler: Scheduler = AndroidSchedulers.mainThread())
			: ObservableTransformer<T, T> = ObservableTransformer {
		it.subscribeOn(Schedulers.io())
				.unsubscribeOn(observableScheduler)
				.observeOn(observerScheduler)
	}

	/**
	 * [Flowable] 线程切换
	 * [flowableScheduler] 发射器所在线程，默认为 [Schedulers.io]
	 * [observeScheduler] 接收器所在线程，默认为 [AndroidSchedulers.mainThread]
	 * return [FlowableTransformer]
	 */
	fun <T> flowableTransformer(flowableScheduler: Scheduler = Schedulers.io(), observeScheduler: Scheduler = AndroidSchedulers.mainThread())
			: FlowableTransformer<T, T> = FlowableTransformer {
		it.subscribeOn(flowableScheduler)
				.unsubscribeOn(flowableScheduler)
				.observeOn(observeScheduler)
	}

}