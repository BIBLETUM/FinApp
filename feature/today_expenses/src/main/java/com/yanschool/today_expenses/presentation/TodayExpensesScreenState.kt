package com.yanschool.today_expenses.presentation

import androidx.compose.runtime.Immutable
import com.yanschool.common_models.TransactionShortUi

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
