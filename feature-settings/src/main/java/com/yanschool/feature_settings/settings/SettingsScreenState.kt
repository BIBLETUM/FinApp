package com.yanschool.feature_settings.settings

sealed interface SettingsScreenState {
    data object Loading : SettingsScreenState
    data class Content(
        val hasAutoLightTheme: Boolean
    ) : SettingsScreenState

    data class Error(val msg: String) : SettingsScreenState

}