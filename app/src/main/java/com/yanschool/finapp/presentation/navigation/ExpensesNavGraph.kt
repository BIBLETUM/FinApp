package com.yanschool.finapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

internal fun NavGraphBuilder.ExpensesNavGraph(
    todayExpensesScreenContent: @Composable () -> Unit,
    historyExpensesScreenContent: @Composable () -> Unit,
) {
    navigation<Screen.ExpensesHome>(startDestination = Screen.TodayExpenses) {
        composable<Screen.TodayExpenses> {
            todayExpensesScreenContent()
        }
        composable<Screen.HistoryExpenses> {
            historyExpensesScreenContent()
        }
    }
}