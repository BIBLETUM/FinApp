package com.yanschool.finapp.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class TransactionDetailUi(
    val id: Int,
    val amount: String,
    val comment: String? = null,
    val category: TransactionCategoryUi,
    val time: String,
)