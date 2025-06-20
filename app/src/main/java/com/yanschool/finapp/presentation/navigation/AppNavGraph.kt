package com.yanschool.finapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
internal fun AppNavGraph(
    navHostController: NavHostController,
    splashScreenContent: @Composable () -> Unit,
    todayExpensesScreenContent: @Composable () -> Unit,
    todayIncomesScreenContent: @Composable () -> Unit,
    balanceScreenContent: @Composable () -> Unit,
    myExpenseCategoriesScreenContent: @Composable () -> Unit,
    settingsScreenContent: @Composable () -> Unit,
    historyExpensesScreenContent: @Composable () -> Unit,
    historyIncomesScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navHostController, startDestination = Screen.Splash
    ) {
        composable<Screen.Splash> {
            splashScreenContent()
        }
        composable<Screen.TodayExpenses> {
            todayExpensesScreenContent()
        }
        composable<Screen.TodayIncomes> {
            todayIncomesScreenContent()
        }
        composable<Screen.Balance> {
            balanceScreenContent()
        }
        composable<Screen.ExpenseCategories> {
            myExpenseCategoriesScreenContent()
        }
        composable<Screen.Settings> {
            settingsScreenContent()
        }
        composable<Screen.HistoryExpenses> {
            historyExpensesScreenContent()
        }
        composable<Screen.HistoryIncomes> {
            historyIncomesScreenContent()
        }
    }
}