package com.yanschool.finapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

internal fun NavGraphBuilder.AccountNavGraph(
    accountScreenContent: @Composable () -> Unit,
    accountSettingsScreenContent: @Composable () -> Unit,
) {
    navigation<Screen.AccountHome>(startDestination = Screen.Account) {
        composable<Screen.Account> {
            accountScreenContent()
        }
        composable<Screen.AccountSettings> {
            accountSettingsScreenContent()
        }
    }
}
