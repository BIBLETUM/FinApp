package com.yanschool.finapp.model

import androidx.compose.runtime.Immutable

@Immutable
data class TransactionCategory(
    val id: Int,
    val name: String,
    val type: TransactionType,
    val description: String?,
    val emoji: String?,
)
