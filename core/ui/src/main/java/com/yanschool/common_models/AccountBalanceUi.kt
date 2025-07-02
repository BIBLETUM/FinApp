package com.yanschool.common_models

import androidx.compose.runtime.Immutable

@Immutable
data class AccountBalanceUi(
    val id: Int,
    val name: String,
    val amount: String,
    val currencySymbol: String,
)
