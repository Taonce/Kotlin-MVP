package com.taonce.kotlindemo.bean

import com.taonce.kotlindemo.base.BaseBean

data class AndroidBean(val count: Int, val results: List<Results>) : BaseBean() {
    override fun toString(): String {
        return "AndroidBean(count=$count, results=$results, error=$error)"
    }

    data class Results(val desc: String,
                       val ganhuo_id: String,
                       val publishedAt: String,
                       val readability: String,
                       val type: String,
                       val url: String,
                       val who: String) {
        override fun toString(): String {
            return "Results(desc='$desc', ganhuo_id='$ganhuo_id', publishedAt='$publishedAt', readability='$readability', type='$type', url='$url', who='$who')"
        }
    }
}