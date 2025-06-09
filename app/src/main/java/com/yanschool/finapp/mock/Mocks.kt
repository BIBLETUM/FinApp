package com.yanschool.finapp.mock

import com.yanschool.finapp.model.TransactionCategoryUi
import com.yanschool.finapp.model.TransactionShortUi
import com.yanschool.finapp.model.TransactionTypeUi

object Mocks {
    val mockCategoriesExpense = listOf(
        TransactionCategoryUi(
            id = 1,
            name = "Аренда квартиры",
            literals = "АК",
            type = TransactionTypeUi.EXPENSE,
            description = null,
            emoji = "\uD83C\uDFE1"
        ),
        TransactionCategoryUi(
            id = 2,
            name = "Одежда",
            literals = "ОД",
            type = TransactionTypeUi.EXPENSE,
            description = null,
            emoji = "\uD83D\uDC57"
        ),
        TransactionCategoryUi(
            id = 3,
            name = "На собачку",
            literals = "НС",
            type = TransactionTypeUi.EXPENSE,
            description = "Джек",
            emoji = "\uD83D\uDC36"
        ),
        TransactionCategoryUi(
            id = 4,
            name = "На собачку",
            literals = "НС",
            type = TransactionTypeUi.EXPENSE,
            description = "Энни",
            emoji = "\uD83D\uDC36"
        ),
        TransactionCategoryUi(
            id = 5,
            name = "Ремонт квартиры",
            literals = "РК",
            type = TransactionTypeUi.EXPENSE,
            description = null,
            emoji = null
        ),
        TransactionCategoryUi(
            id = 6,
            name = "Продукты",
            literals = "ПР",
            type = TransactionTypeUi.EXPENSE,
            description = null,
            emoji = "\uD83C\uDF6D"
        ),
        TransactionCategoryUi(
            id = 7,
            name = "Спортзал",
            literals = "СП",
            type = TransactionTypeUi.EXPENSE,
            description = null,
            emoji = "\uD83C\uDFCB\uFE0F"
        ),
        TransactionCategoryUi(
            id = 8,
            name = "Медицина",
            literals = "МЕ",
            type = TransactionTypeUi.EXPENSE,
            description = null,
            emoji = "\uD83D\uDC8A"
        )
    ).toList()

    val mockTransactionsExpense = mockCategoriesExpense.map { category ->
        TransactionShortUi(
            id = category.id,
            amount = "100 000 ₽",
            category = category
        )
    }.toList()

    val mockCategoriesIncome = listOf(
        TransactionCategoryUi(
            id = 9,
            name = "Зарплата",
            literals = "ЗП",
            type = TransactionTypeUi.INCOME,
            description = null,
            emoji = null,
        ),
        TransactionCategoryUi(
            id = 10,
            name = "Подработка",
            literals = "ПО",
            type = TransactionTypeUi.INCOME,
            description = null,
            emoji = null,
        ),
    ).toList()

    val mockTransactionsIncome = listOf(
        TransactionShortUi(
            id = 9,
            amount = "500 000 ₽",
            category = mockCategoriesIncome[0]
        ),
        TransactionShortUi(
            id = 10,
            amount = "100 000 ₽",
            category = mockCategoriesIncome[1]
        )
    )
}