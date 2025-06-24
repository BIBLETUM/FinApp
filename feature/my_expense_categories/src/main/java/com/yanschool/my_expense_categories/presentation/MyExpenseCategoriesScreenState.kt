package com.yanschool.my_expense_categories.presentation

import com.yanschool.common_models.TransactionCategoryUi

sealed interface MyExpenseCategoriesScreenState {
    data object Loading : MyExpenseCategoriesScreenState
    data class Content(
        val searchQuery: String,
        val expenseCategories: List<TransactionCategoryUi>
    ) : MyExpenseCategoriesScreenState

    data class Error(val msg: String) : MyExpenseCategoriesScreenState

}