package com.example.androidarchitecture.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidarchitecture.ViewModelFactory

fun <T : ViewModel>AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProvider(this, ViewModelFactory.getInstance()).get(viewModelClass)