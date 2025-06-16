package com.yanschool.finapp.presentation.screen.my_expense_categories

import com.yanschool.finapp.presentation.model.TransactionCategoryUi

sealed interface MyExpenseCategoriesScreenState {
    data object Loading : MyExpenseCategoriesScreenState
    data class Content(
        val searchQuery: String,
        val expenseCategories: List<TransactionCategoryUi>
    ) : MyExpenseCategoriesScreenState

    data class Error(val msg: String) : MyExpenseCategoriesScreenState

}