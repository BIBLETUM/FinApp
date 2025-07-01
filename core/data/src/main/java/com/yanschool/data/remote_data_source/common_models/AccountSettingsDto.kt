package com.yanschool.data.remote_data_source.common_models

import com.google.gson.annotations.SerializedName

data class AccountSettingsDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("balance")
    val balance: String,
    @SerializedName("currency")
    val currency: String,
)
