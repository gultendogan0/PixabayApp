package com.gultendogan.pixabayapp.ui.pages.fav

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gultendogan.pixabayapp.data.entity.Hit
import com.gultendogan.pixabayapp.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    private val repository: FavoriteRepository
): ViewModel() {

    val favoriteList = repository.getFavorites
}