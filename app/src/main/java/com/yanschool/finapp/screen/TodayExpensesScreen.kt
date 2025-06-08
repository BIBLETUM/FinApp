package com.yanschool.finapp.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.yanschool.finapp.components.ListItemExpense
import com.yanschool.finapp.model.TransactionCategoryUi
import com.yanschool.finapp.model.TransactionShortUi
import com.yanschool.finapp.model.TransactionTypeUi

private val mockTransactions = mutableListOf<TransactionShortUi>().apply {
    add(
        TransactionShortUi(
            id = 1,
            amount = "100 000 ₽",
            category = TransactionCategoryUi(
                id = 1,
                name = "Аренда квартиры ",
                literals = "АК",
                type = TransactionTypeUi.EXPENSE,
                description = null,
                emoji = "\uD83C\uDFE1",
            )
        )
    )
    add(
        TransactionShortUi(
            id = 2,
            amount = "100 000 ₽",
            category = TransactionCategoryUi(
                id = 2,
                name = "Одежда",
                literals = "ОД",
                type = TransactionTypeUi.EXPENSE,
                description = null,
                emoji = "\uD83D\uDC57",
            )
        )
    )
    add(
        TransactionShortUi(
            id = 3,
            amount = "100 000 ₽",
            category = TransactionCategoryUi(
                id = 3,
                name = "На собачку ",
                literals = "НС",
                type = TransactionTypeUi.EXPENSE,
                description = "Джек",
                emoji = "\uD83D\uDC36",
            )
        )
    )
    add(
        TransactionShortUi(
            id = 4,
            amount = "100 000 ₽",
            category = TransactionCategoryUi(
                id = 4,
                name = "На собачку ",
                literals = "НС",
                type = TransactionTypeUi.EXPENSE,
                description = "Энни",
                emoji = "\uD83D\uDC36",
            )
        )
    )
    add(
        TransactionShortUi(
            id = 5,
            amount = "100 000 ₽",
            category = TransactionCategoryUi(
                id = 5,
                name = "Ремонт квартиры",
                literals = "РК",
                type = TransactionTypeUi.EXPENSE,
                description = null,
                emoji = null,
            )
        )
    )
    add(
        TransactionShortUi(
            id = 6,
            amount = "100 000 ₽",
            category = TransactionCategoryUi(
                id = 6,
                name = "Продукты",
                literals = "ПР",
                type = TransactionTypeUi.EXPENSE,
                description = null,
                emoji = "\uD83C\uDF6D",
            )
        )
    )
    add(
        TransactionShortUi(
            id = 7,
            amount = "100 000 ₽",
            category = TransactionCategoryUi(
                id = 7,
                name = "Спортзал",
                literals = "СП",
                type = TransactionTypeUi.EXPENSE,
                description = null,
                emoji = "\uD83C\uDFCB\uFE0F",
            )
        )
    )
    add(
        TransactionShortUi(
            id = 8,
            amount = "100 000 ₽",
            category = TransactionCategoryUi(
                id = 8,
                name = "Медицина",
                literals = "МЕ",
                type = TransactionTypeUi.EXPENSE,
                description = null,
                emoji = "\uD83D\uDC8A",
            )
        )
    )
    toList()
}

@Composable
fun TodayExpensesScreen() {
    Scaffold { paddingValues ->
        TodayExpensesScreenContent(
            paddingValues = paddingValues
        )
    }
}

@Composable
private fun TodayExpensesScreenContent(
    paddingValues: PaddingValues,
) {
    LazyColumn {

        items(items = mockTransactions, key = { it.id }) {
            ListItemExpense(
                transaction = it
            )
        }
    }
}
