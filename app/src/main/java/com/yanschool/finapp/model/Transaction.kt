package com.yanschool.finapp.model

import androidx.compose.runtime.Immutable

@Immutable
data class Transaction(
    val id: Int,
    val amount: Double,
    val category: TransactionCategory,
)