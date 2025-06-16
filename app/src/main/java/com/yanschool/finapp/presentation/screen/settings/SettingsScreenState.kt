package com.yanschool.finapp.presentation.screen.settings

import com.yanschool.finapp.presentation.model.SettingsUi

sealed interface SettingsScreenState {
    data object Loading : SettingsScreenState
    data class Content(
        val data: SettingsUi,
    ) : SettingsScreenState

    data class Error(val msg: String) : SettingsScreenState

}