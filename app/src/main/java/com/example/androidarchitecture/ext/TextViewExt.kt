package com.example.androidarchitecture.ext

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date

@BindingAdapter("htmlText")
fun TextView.setHtmlText(string: String) {
    text = HtmlCompat.fromHtml(string, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("timeStampFormat")
fun TextView.setTimeStampFormat(timeStamp: Long) {
    try {
        val sdf = SimpleDateFormat("yy-MM-dd HH:mm:ss")
        val date = Date().apply {
            time = timeStamp
        }
        text = sdf.format(date)
    } catch (e: Exception) {
        text = "알 수 없음"
    }
}
