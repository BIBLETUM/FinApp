package com.yanschool.finapp.model

import androidx.compose.runtime.Immutable

@Immutable
data class TransactionShortUi(
    val id: Int,
    val amount: String,
    val category: TransactionCategoryUi,
)