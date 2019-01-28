package com.taonce.kotlindemo.util

import android.widget.Toast
import com.taonce.kotlindemo.App

fun showToast(msg: String) {
    Toast.makeText(App.app.applicationContext,
            msg, Toast.LENGTH_SHORT).show()
}