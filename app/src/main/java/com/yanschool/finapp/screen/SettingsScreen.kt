package com.yanschool.finapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yanschool.components.core.DefaultHorizontalDivider
import com.yanschool.components.core.DefaultTopAppBar
import com.yanschool.finapp.R
import com.yanschool.finapp.components.ListItemSetting
import com.yanschool.finapp.components.ListItemSettingToggle
import com.yanschool.finapp.model.SettingsUi


@Composable
fun SettingsScreenRoot(
    paddingValues: PaddingValues,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        topBar = {
            DefaultTopAppBar(
                titleRes = R.string.settings,
            )
        },
    ) { innerPaddingValues ->
        SettingsScreenContent(
            paddingValues = innerPaddingValues,
            screenState = SettingsUi(
                hasAutoLightTheme = false
            )
        )
    }
}

@Composable
private fun SettingsScreenContent(
    paddingValues: PaddingValues,
    screenState: SettingsUi,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        ListItemSettingToggle(
            titleRes = R.string.auto_light_theme,
            isChecked = screenState.hasAutoLightTheme,
        )
        DefaultHorizontalDivider()
        ListItemSetting(
            titleRes = R.string.main_color,
        )
        DefaultHorizontalDivider()
        ListItemSetting(
            titleRes = R.string.sounds,
        )
        DefaultHorizontalDivider()
        ListItemSetting(
            titleRes = R.string.haptics,
        )
        DefaultHorizontalDivider()
        ListItemSetting(
            titleRes = R.string.code_password,
        )
        DefaultHorizontalDivider()
        ListItemSetting(
            titleRes = R.string.synchronization,
        )
        DefaultHorizontalDivider()
        ListItemSetting(
            titleRes = R.string.language,
        )
        DefaultHorizontalDivider()
        ListItemSetting(
            titleRes = R.string.about_app,
        )
        DefaultHorizontalDivider()
    }
}