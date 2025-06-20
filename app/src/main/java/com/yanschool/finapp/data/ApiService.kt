package com.yanschool.finapp.data

import com.yanschool.finapp.data.common_models.AccountInfoDto
import com.yanschool.finapp.data.common_models.TransactionDto
import com.yanschool.finapp.data.common_models.UserDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("accounts")
    suspend fun getAccounts(): List<UserDto>

    @GET("transactions/account/{id}/period")
    suspend fun getTransactions(
        @Path("id") accountId: Int,
        @Query("startDate") startDate: String? = null,
        @Query("endDate") endDate: String? = null
    ): List<TransactionDto>

    @GET("accounts/{id}")
    suspend fun getAccountInfo(@Path("id") accountId: Int): AccountInfoDto

}