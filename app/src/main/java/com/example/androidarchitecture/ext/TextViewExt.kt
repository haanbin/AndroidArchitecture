package com.example.androidarchitecture.ext

import android.annotation.SuppressLint
import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("htmlText")
fun TextView.setHtmlText(string: String) {
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        (Html.fromHtml(string, Html.FROM_HTML_MODE_COMPACT));
    } else {
        (Html.fromHtml(string));
    }
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
    } catch (e: Exception){
        text = "알 수 없음"
    }
}