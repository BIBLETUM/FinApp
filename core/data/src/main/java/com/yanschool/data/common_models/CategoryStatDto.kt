package com.yanschool.data.common_models

import com.google.gson.annotations.SerializedName

data class CategoryStatDto(
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("categoryName")
    val categoryName: String,
    @SerializedName("emoji")
    val emoji: String,
    @SerializedName("amount")
    val amount: String,
)