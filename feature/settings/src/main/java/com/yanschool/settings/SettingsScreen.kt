package com.yanschool.settings

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.yanschool.components.core.DefaultHorizontalDivider
import com.yanschool.components.core.DefaultTopAppBar
import com.yanschool.ui.R

@Composable
fun SettingsScreenRoot(
    paddingValues: PaddingValues,
) {
    val screenState = remember {
        mutableStateOf(
            SettingsScreenState.Content(
                hasAutoLightTheme = false
            )
        )
    }

    SettingsScreen(paddingValues = paddingValues, screenState = screenState)
}

@Composable
private fun SettingsScreen(
    paddingValues: PaddingValues,
    screenState: State<SettingsScreenState>,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            DefaultTopAppBar(
                titleRes = R.string.settings,
            )
        },
    ) { innerPaddingValues ->
        when (val currentState = screenState.value) {
            is SettingsScreenState.Content -> {
                SettingsScreenContent(
                    paddingValues = innerPaddingValues,
                    screenState = currentState
                )
            }

            is SettingsScreenState.Error -> {
                Log.d("SettingsScreen", currentState.msg)
            }

            is SettingsScreenState.Loading -> {
            }
        }
    }
}

@Composable
private fun SettingsScreenContent(
    paddingValues: PaddingValues,
    screenState: SettingsScreenState.Content,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        ListItemSettingToggle(
            titleRes = R.string.dark_theme,
            isChecked = screenState.hasAutoLightTheme,
        )
        DefaultHorizontalDivider()
        SettingOption.entries.forEach { option ->
            ListItemSetting(
                titleRes = option.titleRes,
            )
            DefaultHorizontalDivider()
        }
    }
}