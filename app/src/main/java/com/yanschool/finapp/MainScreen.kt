package com.yanschool.finapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yanschool.finapp.components.navigation.NavigationBarItemIcon
import com.yanschool.finapp.components.navigation.NavigationBarItemLabel
import com.yanschool.finapp.navigation.AppNavGraph
import com.yanschool.finapp.navigation.Screen
import com.yanschool.finapp.navigation.navigationItems
import com.yanschool.finapp.screen.account_balance.AccountBalanceScreenRoot
import com.yanschool.finapp.screen.my_expense_categories.MyExpenseCategoriesScreenRoot
import com.yanschool.finapp.screen.settings.SettingsScreenRoot
import com.yanschool.finapp.screen.splash.SplashScreenRoot
import com.yanschool.finapp.screen.today_expenses.TodayExpensesScreenRoot
import com.yanschool.finapp.screen.today_incomes.TodayIncomesScreenRoot

@Composable
internal fun MainScreen() {
    val navHostController = rememberNavController()
    var isBottomNavigationEnabled by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(containerColor = MaterialTheme.colorScheme.surface, bottomBar = {
        if (isBottomNavigationEnabled) {
            val backStackEntry by navHostController.currentBackStackEntryAsState()
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            ) {
                navigationItems.forEach { navigationItem ->
                    val selected = backStackEntry?.destination?.hierarchy?.any {
                        it.route == navigationItem.screen.route
                    } ?: false

                    NavigationBarItem(
                        selected = selected,
                        colors = NavigationBarItemDefaults.colors().copy(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.onSurface,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            selectedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
                        ),
                        onClick = {
                            if (!selected) {
                                navHostController.navigate(navigationItem.screen.route) {
                                    popUpTo(Screen.TodayExpenses.route) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = {
                            NavigationBarItemIcon(
                                iconResId = navigationItem.iconResId,
                                titleResId = navigationItem.titleResId
                            )
                        },
                        label = {
                            NavigationBarItemLabel(
                                isSelected = selected,
                                titleResId = navigationItem.titleResId
                            )
                        }
                    )
                }
            }
        }
    }) { paddingValues ->
        AppNavGraph(
            navHostController = navHostController,
            splashScreenContent = {
                SplashScreenRoot(continueToNextScreen = {
                    navHostController.navigate(
                        Screen.TodayExpenses.route
                    ) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                    isBottomNavigationEnabled = true
                })
            },
            todayExpensesScreenContent = {
                TodayExpensesScreenRoot(paddingValues)
            },
            todayIncomesScreenContent = {
                TodayIncomesScreenRoot(paddingValues)
            },
            balanceScreenContent = {
                AccountBalanceScreenRoot(paddingValues)
            },
            myExpenseCategoriesScreenContent = {
                MyExpenseCategoriesScreenRoot(paddingValues)
            },
            settingsScreenContent = {
                SettingsScreenRoot(paddingValues)
            },
        )
    }
}