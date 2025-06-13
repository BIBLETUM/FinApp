package com.yanschool.finapp.navigation

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
) {
    NavHost(
        navController = navHostController, startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            splashScreenContent()
        }
        composable(Screen.TodayExpenses.route) {
            todayExpensesScreenContent()
        }
        composable(Screen.TodayIncomes.route) {
            todayIncomesScreenContent()
        }
        composable(Screen.Balance.route) {
            balanceScreenContent()
        }
        composable(Screen.ExpenseCategories.route) {
            myExpenseCategoriesScreenContent()
        }
        composable(Screen.Settings.route) {
            settingsScreenContent()
        }
    }
}