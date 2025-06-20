package com.yanschool.finapp.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class TransactionCategoryUi(
    val id: Int,
    val name: String,
    val literals: String,
    val type: TransactionTypeUi,
    val emoji: String?,
)
