package com.yanschool.data.remote_data_source.common_models

import com.google.gson.annotations.SerializedName

data class TransactionDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("category")
    val category: TransactionCategoryDto,
    @SerializedName("amount")
    val amount: String,
    @SerializedName("transactionDate")
    val transactionDate: String,
    @SerializedName("comment")
    val comment: String?,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
)
