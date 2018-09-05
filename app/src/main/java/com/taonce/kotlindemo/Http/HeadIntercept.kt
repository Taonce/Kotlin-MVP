package com.taonce.kotlindemo.Http

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeadIntercept : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        var request: Request = chain!!.request()
                .newBuilder()
//                .addHeader()
                .build()
        return chain!!.proceed(request)
    }

}

