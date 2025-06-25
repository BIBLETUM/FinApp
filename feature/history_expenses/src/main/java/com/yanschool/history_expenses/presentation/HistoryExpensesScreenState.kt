package com.yanschool.history_expenses.presentation

import androidx.compose.runtime.Immutable
import com.yanschool.common_models.TransactionDetailUi
import com.yanschool.common_screen_layout.history.HistoryScreenStateContent

@Immutable
interface HistoryExpensesScreenState {

    @Immutable
    data object Loading : HistoryExpensesScreenState

    @Immutable
    data class Content(
        override val startDate: String,
        override val endDate: String,
        override val total: String,
        override val transactions: List<TransactionDetailUi>,
    ) : HistoryExpensesScreenState, HistoryScreenStateContent

    @Immutable
    data class Error(val msg: String) : HistoryExpensesScreenState

}