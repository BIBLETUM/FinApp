package com.yanschool.finapp.model

import androidx.compose.runtime.Immutable

@Immutable
data class MyExpenseCategoriesUi(
    val searchQuery: String,
    val expenseCategories: List<TransactionCategoryUi>
)
