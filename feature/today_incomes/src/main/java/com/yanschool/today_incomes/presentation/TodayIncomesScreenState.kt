package com.yanschool.today_incomes.presentation

import androidx.compose.runtime.Immutable
import com.yanschool.common_models.TransactionShortUi

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