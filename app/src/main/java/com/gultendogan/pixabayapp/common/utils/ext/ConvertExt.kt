package com.gultendogan.pixabayapp.common.utils.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>