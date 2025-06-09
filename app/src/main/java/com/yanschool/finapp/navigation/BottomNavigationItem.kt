package com.yanschool.finapp.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.yanschool.finapp.R

internal enum class BottomNavigationItem(
    val screen: Screen,
    @DrawableRes val iconResId: Int,
    @StringRes val titleResId: Int,
) {
    TodayExpenses(
        screen = Screen.TodayExpenses,
        iconResId = R.drawable.expenses_ic,
        titleResId = R.string.expenses
    ),
    TodayIncomes(
        screen = Screen.TodayIncomes,
        iconResId = R.drawable.incomes_ic,
        titleResId = R.string.incomes
    ),
    Balance(
        screen = Screen.Balance,
        iconResId = R.drawable.balance_ic,
        titleResId = R.string.score
    ),
    ExpenseCategories(
        screen = Screen.ExpenseCategories,
        iconResId = R.drawable.expense_categories_ic,
        titleResId = R.string.expense_categories
    ),
    Settings(
        screen = Screen.Settings,
        iconResId = R.drawable.settings_ic,
        titleResId = R.string.settings
    ),
}