package com.yanschool.common_models

import androidx.compose.runtime.Immutable

@Immutable
data class TransactionShortUi(
    val id: Int,
    val amount: String,
    val comment: String? = null,
    val category: TransactionCategoryUi,
)
