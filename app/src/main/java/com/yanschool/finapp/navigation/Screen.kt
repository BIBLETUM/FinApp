package com.yanschool.finapp.navigation

internal sealed class Screen(
    val route: String,
) {
    data object Splash : Screen(ROUTE_SPLASH)

    data object TodayExpenses : Screen(ROUTE_TODAY_EXPENSES)
    data object TodayIncomes : Screen(ROUTE_TODAY_INCOMES)
    data object Balance : Screen(ROUTE_BALANCE)
    data object ExpenseCategories : Screen(ROUTE_EXPENSE_CATEGORIES)
    data object Settings : Screen(ROUTE_SETTINGS)

    companion object {
        const val ROUTE_SPLASH = "Splash_screen_route"

        const val ROUTE_TODAY_EXPENSES = "Today_expenses_screen_route"
        const val ROUTE_TODAY_INCOMES = "Today_incomes_screen_route"
        const val ROUTE_BALANCE = "Balance_screen_route"
        const val ROUTE_EXPENSE_CATEGORIES = "Expense_categories_screen_route"
        const val ROUTE_SETTINGS = "Settings_screen_route"
    }
}