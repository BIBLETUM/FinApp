package com.yanschool.data.common_models

import com.google.gson.annotations.SerializedName

data class TransactionCategoryDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("emoji")
    val emoji: String,
    @SerializedName("isIncome")
    val isIncome: Boolean,
)