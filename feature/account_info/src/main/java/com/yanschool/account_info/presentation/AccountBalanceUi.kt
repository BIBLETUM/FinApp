package com.yanschool.account_info.presentation

import androidx.compose.runtime.Immutable

@Immutable
data class AccountBalanceUi(
    val amount: String,
    val currency: String,
)
