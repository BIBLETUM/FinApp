package com.yanschool.finapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

internal fun NavGraphBuilder.IncomesNavGraph(
    todayIncomesScreenContent: @Composable () -> Unit,
    historyIncomesScreenContent: @Composable () -> Unit,
) {
    navigation<Screen.IncomesHome>(startDestination = Screen.TodayIncomes) {
        composable<Screen.TodayIncomes> {
            todayIncomesScreenContent()
        }
        composable<Screen.HistoryIncomes> {
            historyIncomesScreenContent()
        }
    }
}