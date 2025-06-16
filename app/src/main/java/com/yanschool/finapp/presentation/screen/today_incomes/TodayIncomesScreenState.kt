package com.yanschool.finapp.presentation.screen.today_incomes

import com.yanschool.finapp.presentation.model.DailyTransactionGroupUi

sealed interface TodayIncomesScreenState {
    data object Loading : TodayIncomesScreenState
    data class Content(
        val data: DailyTransactionGroupUi,
    ) : TodayIncomesScreenState

    data class Error(val msg: String) : TodayIncomesScreenState
}