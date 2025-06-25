package com.yanschool.finapp.presentation.screen.history_income

import androidx.compose.runtime.Immutable
import com.yanschool.finapp.presentation.model.TransactionDetailUi
import com.yanschool.finapp.presentation.screen.HistoryScreenStateContent

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