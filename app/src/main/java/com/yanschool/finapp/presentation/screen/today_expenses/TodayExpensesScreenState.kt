package com.yanschool.finapp.presentation.screen.today_expenses

import com.yanschool.finapp.presentation.model.DailyTransactionGroupUi

sealed interface TodayExpensesScreenState {
    data object Loading : TodayExpensesScreenState
    data class Content(
        val data: DailyTransactionGroupUi,
    ) : TodayExpensesScreenState

    data class Error(val msg: String) : TodayExpensesScreenState
}