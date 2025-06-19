package com.yanschool.finapp.presentation.screen.today_expenses

import androidx.compose.runtime.Immutable
import com.yanschool.finapp.presentation.model.TransactionShortUi

@Immutable
sealed interface TodayExpensesScreenState {
    @Immutable
    data object Loading : TodayExpensesScreenState

    @Immutable
    data class Content(
        val total: String,
        val transactionShortUis: List<TransactionShortUi>,
    ) : TodayExpensesScreenState

    @Immutable
    data class Error(val msg: String) : TodayExpensesScreenState
}