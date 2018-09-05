package com.taonce.kotlindemo.bean

import com.taonce.kotlindemo.base.BaseBean


/**
 * Author: taoyongxiang
 * Date: 2018/9/4
 * Project: KotlinDemo
 * Desc:
 */

data class AndroidBean(var count: Int, var results: Collection<Results>, var error: String) : BaseBean(error) {
    override fun toString(): String {
        return "AndroidBean(error=$error count=$count, results=$results)"
    }
}

data class Results(var desc: String,
                   var ganhuo_id: String,
                   var publishedAt: String,
                   var readability: String,
                   var type: String,
                   var url: String,
                   var who: String) {
    override fun toString(): String {
        return "Results(desc='$desc', ganhuo_id='$ganhuo_id', publishedAt='$publishedAt', readability='$readability', type='$type', url='$url', who='$who')"
    }
}