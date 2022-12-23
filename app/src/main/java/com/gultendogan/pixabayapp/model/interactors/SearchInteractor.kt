package com.gultendogan.pixabayapp.model.interactors

import com.gultendogan.pixabayapp.common.base.interactors.BaseNetworkInteractor
import com.gultendogan.pixabayapp.model.network.api.PixabayApi

class SearchInteractor(
    private val api: PixabayApi
) : BaseNetworkInteractor() {

    fun search(query: String) = safeApiCall {
        api.search(query)
    }

}