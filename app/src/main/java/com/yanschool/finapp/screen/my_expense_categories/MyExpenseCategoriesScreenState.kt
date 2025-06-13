package com.yanschool.finapp.screen.my_expense_categories

import com.yanschool.finapp.model.MyExpenseCategoriesUi

sealed interface MyExpenseCategoriesScreenState {
    data object Loading : MyExpenseCategoriesScreenState
    data class Content(
        val data: MyExpenseCategoriesUi,
    ) : MyExpenseCategoriesScreenState

    data class Error(val msg: String) : MyExpenseCategoriesScreenState

}