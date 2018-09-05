package com.taonce.kotlindemo.Http

import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class OkHttpUtil private constructor() {
    private var cache: Cache = CacheUtil.mInstance.getCache()!!
    private var okHttpClient: OkHttpClient? = null
    private var okHttpLog: OkHttpLog = OkHttpLog()
    private val Connection_time: Long = 10
    private val Write_time: Long = 10
    private val Read_time: Long = 30

    companion object {
        val mInstance: OkHttpUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            OkHttpUtil()
        }
    }

    fun getHttpClient(): OkHttpClient? {
        if (null == okHttpClient) {
            okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(Connection_time, TimeUnit.SECONDS)
                    .readTimeout(Read_time, TimeUnit.SECONDS)
                    .writeTimeout(Write_time, TimeUnit.SECONDS)
                    .addInterceptor(LogInterceptor(okHttpLog))
                    .addInterceptor(HeadIntercept())
                    .cache(cache)
                    .build()
        }
        return okHttpClient
    }

}
