package com.taonce.kotlindemo.Http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Author: taoyongxiang
 * Date: 2018/9/4
 * Project: KotlinDemo
 * Desc:
 */

class RetrofitUtil private constructor() {
    private val okHttpClient: OkHttpClient = OkHttpUtil.mInstance.getHttpClient()!!
    private var retrofit: Retrofit? = null

    companion object {
        val mInstance: RetrofitUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitUtil()
        }
    }

    private fun getRetrofit(): Retrofit? {
        if (null == retrofit) {
            retrofit = Retrofit.Builder()
                    .baseUrl("https://gank.io/api/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
        }
        return retrofit
    }

    fun getService(): BaseService {
        return RetrofitUtil.mInstance.getRetrofit()!!.create(BaseService::class.java)
    }
}