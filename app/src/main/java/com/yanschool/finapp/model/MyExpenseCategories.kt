package com.yanschool.finapp.model

import androidx.compose.runtime.Immutable

@Immutable
data class MyExpenseCategories(
    val searchQuery: String,
    val expenseCategories: List<TransactionCategory>
)
