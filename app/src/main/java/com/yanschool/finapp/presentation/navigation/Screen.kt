package com.yanschool.finapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
internal sealed interface Screen {

    @Serializable
    data object Splash : Screen

    @Serializable
    data object ExpensesHome : Screen

    @Serializable
    data object TodayExpenses : Screen

    @Serializable
    data object HistoryExpenses : Screen

    @Serializable
    data object IncomesHome : Screen

    @Serializable
    data object TodayIncomes : Screen

    @Serializable
    data object AccountHome : Screen

    @Serializable
    data object Account : Screen

    @Serializable
    data object AccountSettings : Screen

    @Serializable
    data object ExpenseCategories : Screen

    @Serializable
    data object Settings : Screen

    @Serializable
    data object HistoryIncomes : Screen
}
