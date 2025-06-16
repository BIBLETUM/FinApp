package com.yanschool.finapp.di

import com.yanschool.finapp.data.ApiFactory
import com.yanschool.finapp.data.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiService(): ApiService {
        return ApiFactory.apiService
    }
}