package com.taonce.mvp.util

import android.widget.Toast
import com.taonce.mvp.App

fun showToast(msg: String) {
    Toast.makeText(App.app.applicationContext,
            msg, Toast.LENGTH_SHORT).show()
}