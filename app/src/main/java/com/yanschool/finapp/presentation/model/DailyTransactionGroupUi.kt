package com.yanschool.finapp.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class DailyTransactionGroupUi(
    val type: TransactionTypeUi,
    val total: String,
    val transactionShortUis: List<TransactionShortUi>,
)
