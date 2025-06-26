package com.yanschool.domain.common_models

data class TransactionShort(
    val id: Int,
    val amount: String,
    val comment: String? = null,
    val category: TransactionCategory,
)
