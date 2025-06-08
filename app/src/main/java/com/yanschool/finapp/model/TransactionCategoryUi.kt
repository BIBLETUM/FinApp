package com.yanschool.finapp.model

import androidx.compose.runtime.Immutable

@Immutable
data class TransactionCategoryUi(
    val id: Int,
    val name: String,
    val literals: String,
    val type: TransactionTypeUi,
    val description: String?,
    val emoji: String?,
)
