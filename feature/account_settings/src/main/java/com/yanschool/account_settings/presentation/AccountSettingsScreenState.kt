package com.yanschool.account_settings.presentation

sealed class AccountSettingsScreenState(val isAbleToSave: Boolean = false) {
    data object Loading : AccountSettingsScreenState()
    data class Content(
        val name: String,
        val balance: String,
        val currency: Currency,
        val isBalanceError: Boolean,
        val isNameError: Boolean,
    ) : AccountSettingsScreenState(isAbleToSave = !(isBalanceError || isNameError))

    data object ChangesSaved : AccountSettingsScreenState()

    data class Error(val msg: String) : AccountSettingsScreenState()
}
