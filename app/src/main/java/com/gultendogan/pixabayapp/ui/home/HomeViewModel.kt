package com.gultendogan.pixabayapp.ui.home

import androidx.lifecycle.*
import com.gultendogan.pixabayapp.data.ApiFactory
import com.gultendogan.pixabayapp.data.PixabayBean
import com.gultendogan.pixabayapp.repository.HomeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepositoryImpl,
    private val apiFactory: ApiFactory
): ViewModel() {

    private val _characterList = MutableLiveData<PixabayBean>()
    val characterList: LiveData<PixabayBean> get() = _characterList

    fun getData(
    ) = viewModelScope.launch(Dispatchers.IO){
        _characterList.postValue(repository.getImages())
    }
}



