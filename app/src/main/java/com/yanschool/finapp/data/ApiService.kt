package com.yanschool.finapp.data

import com.yanschool.finapp.data.common_models.AccountInfoDto
import com.yanschool.finapp.data.common_models.TransactionDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("transactions/account/1/period")
    suspend fun getTransactions(
        @Query("startDate") startDate: String? = null,
        @Query("endDate") endDate: String? = null
    ): List<TransactionDto>

    @GET("accounts/1")
    suspend fun getAccountInfo(): AccountInfoDto

}