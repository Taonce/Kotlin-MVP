package com.taonce.kotlindemo.Http

import android.util.Log
import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor

class OkHttpLog : LogInterceptor.Logger {
    override fun log(message: String?) {
        Log.d("aulton", message)
    }

}

