package com.yanschool.account_settings.presentation

sealed interface AccountSettingsScreenActions {

    data class SetNewName(val name: String) : AccountSettingsScreenActions
    data class SetNewCurrency(val currency: Currency) : AccountSettingsScreenActions
    data class SetNewBalance(val balance: String) : AccountSettingsScreenActions
    data object SaveChanges : AccountSettingsScreenActions
}
