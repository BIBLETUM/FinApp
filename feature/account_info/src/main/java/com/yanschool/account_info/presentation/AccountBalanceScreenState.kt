package com.yanschool.account_info.presentation

import com.yanschool.common_models.AccountBalanceUi

sealed interface AccountBalanceScreenState {

    data object Loading : AccountBalanceScreenState
    data class Content(
        val data: AccountBalanceUi,
    ) : AccountBalanceScreenState

    data class Error(val msg: String) : AccountBalanceScreenState
}
