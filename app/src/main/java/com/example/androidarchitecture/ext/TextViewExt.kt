package com.example.androidarchitecture.ext

import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("htmlText")
fun TextView.setHtmlText(string: String) {
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        (Html.fromHtml(string, Html.FROM_HTML_MODE_COMPACT));
    } else {
        (Html.fromHtml(string));
    }
}