package com.gultendogan.pixabayapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import com.gultendogan.pixabayapp.model.interactors.DiscoveryInteractor
import com.gultendogan.pixabayapp.model.interactors.SearchInteractor
import com.gultendogan.pixabayapp.data.api.PixabayApi

@Module
@InstallIn(ViewModelComponent::class)
object InteractionModule {

    @ViewModelScoped
    @Provides
    fun getSearchInteraction(
        pixabayApi: PixabayApi
    ): SearchInteractor {
        return SearchInteractor(pixabayApi)
    }

    @ViewModelScoped
    @Provides
    fun getDiscoverInteraction(
        pixabayApi: PixabayApi
    ): DiscoveryInteractor {
        return DiscoveryInteractor(pixabayApi)
    }

}