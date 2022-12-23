package com.gultendogan.pixabayapp.model.interactors

import com.gultendogan.pixabayapp.common.base.interactors.BaseNetworkInteractor
import com.gultendogan.pixabayapp.model.network.api.PixabayApi

class DiscoveryInteractor(
    private val api: PixabayApi
) : BaseNetworkInteractor() {

    fun getEditorsChoice() = safeApiCall {
        api.editorsChoice()
    }

}