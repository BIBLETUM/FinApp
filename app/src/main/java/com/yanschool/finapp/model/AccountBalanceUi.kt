package com.yanschool.finapp.model

import androidx.compose.runtime.Immutable

@Immutable
data class AccountBalanceUi(
    val amount: String,
    val currency: String,
)
