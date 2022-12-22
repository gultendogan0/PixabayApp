package com.gultendogan.pixabayapp.ui.home

import androidx.lifecycle.*
import com.gultendogan.pixabayapp.data.ApiFactory
import com.gultendogan.pixabayapp.data.Hit
import com.gultendogan.pixabayapp.repository.HomeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepositoryImpl,
    private val apiFactory: ApiFactory
): ViewModel() {

    val characterList: MutableLiveData<List<Hit>> = MutableLiveData()

    fun getData(
    ) = viewModelScope.launch(Dispatchers.IO){
        characterList.postValue(repository.getImages())
    }
}



