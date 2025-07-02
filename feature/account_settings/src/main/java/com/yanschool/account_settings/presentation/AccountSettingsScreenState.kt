package com.yanschool.account_settings.presentation

sealed interface AccountSettingsScreenState {
    data object Loading : AccountSettingsScreenState
    data class Content(
        val name: String,
        val balance: String,
        val currency: Currency,
    ) : AccountSettingsScreenState

    data object ChangesSaved : AccountSettingsScreenState

    data class Error(val msg: String) : AccountSettingsScreenState
}
