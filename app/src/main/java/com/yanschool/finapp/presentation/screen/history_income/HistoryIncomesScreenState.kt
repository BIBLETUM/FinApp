package com.yanschool.finapp.presentation.screen.history_income

import androidx.compose.runtime.Immutable
import com.yanschool.finapp.presentation.model.TransactionDetailUi

@Immutable
interface HistoryIncomesScreenState {

    @Immutable
    data object Loading : HistoryIncomesScreenState

    @Immutable
    data class Content(
        val startDate: String,
        val endDate: String,
        val total: String,
        val transactions: List<TransactionDetailUi>,
    ) : HistoryIncomesScreenState

    @Immutable
    data class Error(val msg: String) : HistoryIncomesScreenState


}