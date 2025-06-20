package com.yanschool.finapp.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
    data class AccountBalanceUi(
    val amount: String,
    val currency: String,
)
