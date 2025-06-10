package com.yanschool.finapp.screen.account_balance

import com.yanschool.finapp.model.AccountBalanceUi

sealed interface AccountBalanceScreenState {

    data object Loading : AccountBalanceScreenState
    data class Content(
        val data: AccountBalanceUi,
    ) : AccountBalanceScreenState

    data class Error(val msg: String) : AccountBalanceScreenState

}