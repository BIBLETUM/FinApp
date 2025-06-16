package com.yanschool.finapp.data

import com.yanschool.finapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiFactory {

    private const val BASE_URL = "https://shmr-finance.ru/api/v1/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(BuildConfig.apiKey))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val apiService: ApiService = retrofit.create()

}