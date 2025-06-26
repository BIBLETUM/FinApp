package com.yanschool.history_incomes.presentation

import androidx.compose.runtime.Immutable
import com.yanschool.common_models.TransactionDetailUi
import com.yanschool.common_screen_layout.history.HistoryScreenStateContent

@Immutable
interface HistoryIncomesScreenState {

    @Immutable
    data object Loading : HistoryIncomesScreenState

    @Immutable
    data class Content(
        override val startDate: String,
        override val endDate: String,
        override val total: String,
        override val transactions: List<TransactionDetailUi>,
    ) : HistoryIncomesScreenState, HistoryScreenStateContent

    @Immutable
    data class Error(val msg: String) : HistoryIncomesScreenState
}
