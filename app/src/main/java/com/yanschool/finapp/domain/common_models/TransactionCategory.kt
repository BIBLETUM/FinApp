package com.yanschool.finapp.domain.common_models

data class TransactionCategory(
    val id: Int,
    val name: String,
    val emoji: String,
    val isIncome: Boolean,
)