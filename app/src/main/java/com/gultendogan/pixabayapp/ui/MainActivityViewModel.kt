package com.gultendogan.pixabayapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gultendogan.pixabayapp.common.utils.ext.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    private val _toolbarLiftedObservable = MutableLiveData<Boolean>()
    val toolbarLiftedObservable = _toolbarLiftedObservable.asLiveData()
    fun setToolbarLifted(isLifted: Boolean) {
        _toolbarLiftedObservable.postValue(isLifted)
    }
}