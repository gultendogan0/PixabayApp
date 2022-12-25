package com.gultendogan.pixabayapp.ui.pages.discover

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.gultendogan.pixabayapp.common.base.wrappers.ResponseWrapper
import com.gultendogan.pixabayapp.data.entity.Hit
import com.gultendogan.pixabayapp.utils.ext.asLiveData
import com.gultendogan.pixabayapp.model.interactors.DiscoveryInteractor
import com.gultendogan.pixabayapp.data.entity.PixaBayResponse
import com.gultendogan.pixabayapp.data.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val discoveryInteractor: DiscoveryInteractor,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _discoveryObservable = MutableLiveData<ResponseWrapper<PixaBayResponse>>()
    val discoveryObservable get() = _discoveryObservable.asLiveData()

    private val TAG = "TAG"
    fun getDiscover() {
        viewModelScope.launch {
            discoveryInteractor.getEditorsChoice().collect {
                Log.d(TAG, "getDiscover: $it")
                _discoveryObservable.postValue(it)
            }
        }
    }

    fun insertFavorite(hit: Hit){
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.insertFavorite(hit)
        }
    }
}