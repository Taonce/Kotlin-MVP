package com.taonce.kotlindemo.Http

import com.taonce.kotlindemo.App
import okhttp3.Cache
import java.io.File

class CacheUtil private constructor() {
    private var mCache: Cache? = null
    private var cacheFile: File = File(App.app.cacheDir.absolutePath)
    private val maxSize: Long = 8 * 1024 * 1024


    companion object {
        val mInstance: CacheUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { CacheUtil() }
    }

    fun getCache(): Cache? {

        if (null == mCache) {
            mCache = Cache(cacheFile, maxSize)
        }
        return mCache
    }

    init {
        if (!cacheFile.exists()) cacheFile.mkdir()
    }
}

