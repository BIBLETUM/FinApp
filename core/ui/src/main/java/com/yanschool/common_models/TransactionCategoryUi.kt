package com.yanschool.common_models

import androidx.compose.runtime.Immutable

@Immutable
data class TransactionCategoryUi(
    val id: Int,
    val name: String,
    val literals: String,
    val type: TransactionTypeUi,
    val emoji: String?,
)