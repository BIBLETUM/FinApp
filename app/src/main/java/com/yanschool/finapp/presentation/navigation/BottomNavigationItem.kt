package com.yanschool.finapp.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.yanschool.finapp.ui.R

internal enum class BottomNavigationItem(
    val screen: Screen,
    @DrawableRes val iconResId: Int,
    @StringRes val titleResId: Int,
) {
    TodayExpenses(
        screen = Screen.ExpensesHome,
        iconResId = R.drawable.expenses_ic,
        titleResId = R.string.expenses
    ),
    TodayIncomes(
        screen = Screen.IncomesHome,
        iconResId = R.drawable.incomes_ic,
        titleResId = R.string.incomes
    ),
    Balance(
        screen = Screen.AccountHome,
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
