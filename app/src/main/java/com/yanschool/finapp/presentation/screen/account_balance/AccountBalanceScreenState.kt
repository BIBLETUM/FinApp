package com.yanschool.finapp.presentation.screen.account_balance

import com.yanschool.finapp.presentation.model.AccountBalanceUi

sealed interface AccountBalanceScreenState {

    data object Loading : AccountBalanceScreenState
    data class Content(
        val data: AccountBalanceUi,
    ) : AccountBalanceScreenState

    data class Error(val msg: String) : AccountBalanceScreenState

}