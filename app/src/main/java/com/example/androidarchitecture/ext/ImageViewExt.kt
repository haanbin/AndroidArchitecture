package com.example.androidarchitecture.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    url.apply {
        Glide.with(context).load(url).into(this@setImageUrl)
    }
}
