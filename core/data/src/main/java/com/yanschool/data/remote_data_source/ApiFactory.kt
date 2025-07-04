package com.yanschool.data.remote_data_source

import com.yanschool.data.remote_data_source.api.AccountInfoService
import com.yanschool.data.remote_data_source.api.AccountSettingsService
import com.yanschool.data.remote_data_source.api.AccountsService
import com.yanschool.data.remote_data_source.api.TransactionsService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val BASE_URL = "https://shmr-finance.ru/api/v1/"

    private fun createOkHttpClient(apiKey: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(apiKey))
            .build()
    }

    fun createRetrofit(apiKey: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient(apiKey))
            .build()
    }

    fun createAccountsService(retrofit: Retrofit): AccountsService {
        return retrofit.create(AccountsService::class.java)
    }

    fun createAccountInfoService(retrofit: Retrofit): AccountInfoService {
        return retrofit.create(AccountInfoService::class.java)
    }

    fun createTransactionsService(retrofit: Retrofit): TransactionsService {
        return retrofit.create(TransactionsService::class.java)
    }

    fun createAccountSettingsService(retrofit: Retrofit): AccountSettingsService {
        return retrofit.create(AccountSettingsService::class.java)
    }
}
