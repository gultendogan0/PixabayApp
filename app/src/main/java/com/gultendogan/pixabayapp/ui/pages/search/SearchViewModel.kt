package com.gultendogan.pixabayapp.ui.pages.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.gultendogan.pixabayapp.common.base.wrappers.ResponseWrapper
import com.gultendogan.pixabayapp.common.utils.ext.asLiveData
import com.gultendogan.pixabayapp.model.interactors.SearchInteractor
import com.gultendogan.pixabayapp.model.network.response.PixaBayResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchInteractor: SearchInteractor
) : ViewModel() {

    private val _searchObservable = MutableLiveData<ResponseWrapper<PixaBayResponse>>()
    val searchObservable get() = _searchObservable.asLiveData()

    private var queryString: String = ""

    fun setQuery(query: String?) {
        queryString = query ?: ""
    }

    fun search() {
        viewModelScope.launch {
            searchInteractor.search(queryString).collect {
                _searchObservable.postValue(it)
            }
        }
    }
}