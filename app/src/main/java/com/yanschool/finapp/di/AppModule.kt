package com.yanschool.finapp.di

import com.yanschool.data.remote_data_source.ApiFactory
import com.yanschool.data.remote_data_source.api.AccountInfoService
import com.yanschool.data.remote_data_source.api.AccountsService
import com.yanschool.data.remote_data_source.api.TransactionsService
import com.yanschool.finapp.app.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
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
    fun provideRetrofit(
        @Named("ApiKey") apiKey: String,
    ): Retrofit {
        return ApiFactory.createRetrofit(apiKey)
    }

    @Singleton
    @Provides
    fun provideAccountsService(
        retrofit: Retrofit
    ): AccountsService {
        return ApiFactory.createAccountsService(retrofit)
    }

    @Singleton
    @Provides
    fun provideAccountInfoService(
        retrofit: Retrofit
    ): AccountInfoService {
        return ApiFactory.createAccountInfoService(retrofit)
    }

    @Singleton
    @Provides
    fun provideTransactionsService(
        retrofit: Retrofit
    ): TransactionsService {
        return ApiFactory.createTransactionsService(retrofit)
    }

}