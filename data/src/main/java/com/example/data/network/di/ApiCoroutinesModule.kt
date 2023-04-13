package com.example.data.network.di

import com.example.data.network.ApiClient
import com.example.data.network.api.BookApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class ApiCoroutinesModule {
    @Provides
    @Singleton
    fun provideBookApi(apiClient: ApiClient): BookApi {
        return apiClient.provideBookApi()
    }
}
