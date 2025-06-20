package com.yanschool.finapp.presentation.screen.today_incomes

import androidx.compose.runtime.Immutable
import com.yanschool.finapp.presentation.model.TransactionShortUi

@Immutable
sealed interface TodayIncomesScreenState {
    @Immutable
    data object Loading : TodayIncomesScreenState

    @Immutable
    data class Content(
        val total: String,
        val transactionShortUis: List<TransactionShortUi>,
    ) : TodayIncomesScreenState

    @Immutable
    data class Error(val msg: String) : TodayIncomesScreenState
}