package com.yanschool.data.remote_data_source.common_models

import com.google.gson.annotations.SerializedName

data class AccountShortDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("balance")
    val balance: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
)
