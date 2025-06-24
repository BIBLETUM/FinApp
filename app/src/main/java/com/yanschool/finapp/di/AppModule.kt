package com.yanschool.finapp.di

import com.yanschool.data.ApiFactory
import com.yanschool.data.ApiService
import com.yanschool.finapp.app.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("ApiKey")
    fun provideApiKey(): String {
        return BuildConfig.apiKey
    }

    @Singleton
    @Provides
    fun provideApiService(
        @Named("ApiKey") apiKey: String,
    ): ApiService {
        return ApiFactory.create(apiKey)
    }

}