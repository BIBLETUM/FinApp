package com.yanschool.finapp.presentation.screen.history_expenses

import androidx.compose.runtime.Immutable
import com.yanschool.finapp.presentation.model.TransactionDetailUi

@Immutable
interface HistoryExpensesScreenState {

    @Immutable
    data object Loading : HistoryExpensesScreenState

    @Immutable
    data class Content(
        val startDate: String,
        val endDate: String,
        val total: String,
        val transactions: List<TransactionDetailUi>,
    ) : HistoryExpensesScreenState

    @Immutable
    data class Error(val msg: String) : HistoryExpensesScreenState


}