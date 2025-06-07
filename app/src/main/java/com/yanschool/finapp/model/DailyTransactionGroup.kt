package com.yanschool.finapp.model

import androidx.compose.runtime.Immutable

@Immutable
data class DailyTransactionGroup(
    val type: TransactionType,
    val total: String,
    val transactions: List<Transaction>,
)
