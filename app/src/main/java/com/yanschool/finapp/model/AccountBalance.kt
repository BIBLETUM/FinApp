package com.yanschool.finapp.model

import androidx.compose.runtime.Immutable

@Immutable
data class AccountBalance(
    val amount: Int,
    val currency: String,
)
