package com.yanschool.finapp.domain.transaction_categories

data class TransactionCategory(
    val id: Int,
    val name: String,
    val emoji: String,
    val isIncome: Boolean,
)