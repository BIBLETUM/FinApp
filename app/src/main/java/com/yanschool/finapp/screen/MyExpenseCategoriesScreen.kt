package com.yanschool.finapp.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yanschool.components.core.DefaultHorizontalDivider
import com.yanschool.components.core.DefaultTopAppBar
import com.yanschool.finapp.R
import com.yanschool.finapp.components.ListItemSearch
import com.yanschool.finapp.components.ListItemTransactionCategory
import com.yanschool.finapp.mock.Mocks
import com.yanschool.finapp.model.MyExpenseCategoriesUi

@Composable
fun MyExpenseCategoriesScreenRoot(
    paddingValues: PaddingValues,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        topBar = {
            DefaultTopAppBar(
                titleRes = R.string.my_expense_categories,
            )
        },
    ) { innerPaddingValues ->
        MyExpenseCategoriesScreenContent(
            paddingValues = innerPaddingValues,
            screenState = MyExpenseCategoriesUi(
                searchQuery = "",
                expenseCategories = Mocks.mockCategoriesExpense
            )
        )
    }
}

@Composable
private fun MyExpenseCategoriesScreenContent(
    paddingValues: PaddingValues,
    screenState: MyExpenseCategoriesUi,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        item {
            ListItemSearch(query = screenState.searchQuery)
            DefaultHorizontalDivider()
        }

        items(items = screenState.expenseCategories, key = { it.id }) {
            ListItemTransactionCategory(category = it)
            DefaultHorizontalDivider()
        }
    }
}