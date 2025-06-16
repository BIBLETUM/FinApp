package com.yanschool.finapp.data

import com.yanschool.finapp.data.transaction_categories.TransactionCategoryDto
import retrofit2.http.GET

interface ApiService {

    @GET("categories")
    suspend fun getTransactionCategories(): List<TransactionCategoryDto>

}