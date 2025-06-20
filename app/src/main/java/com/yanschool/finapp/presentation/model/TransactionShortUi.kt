package com.yanschool.finapp.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class TransactionShortUi(
    val id: Int,
    val amount: String,
    val comment: String? = null,
    val category: TransactionCategoryUi,
)