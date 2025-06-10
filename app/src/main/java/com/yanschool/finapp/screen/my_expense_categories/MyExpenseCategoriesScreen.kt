package com.yanschool.finapp.screen.my_expense_categories

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val screenState = remember {
        mutableStateOf(
            MyExpenseCategoriesScreenState.Content(
                MyExpenseCategoriesUi(
                    searchQuery = "",
                    expenseCategories = Mocks.mockCategoriesExpense
                )
            )
        )
    }

    MyExpenseCategoriesScreen(paddingValues = paddingValues, screenState = screenState)
}

@Composable
private fun MyExpenseCategoriesScreen(
    paddingValues: PaddingValues,
    screenState: State<MyExpenseCategoriesScreenState>,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            DefaultTopAppBar(
                titleRes = R.string.my_expense_categories,
            )
        },
    ) { innerPaddingValues ->
        when (val currentState = screenState.value) {
            is MyExpenseCategoriesScreenState.Content -> {
                MyExpenseCategoriesScreenContent(
                    paddingValues = innerPaddingValues,
                    screenState = currentState
                )
            }

            is MyExpenseCategoriesScreenState.Error -> {
                Log.d("MyExpenseCategoriesScreen", currentState.msg)
            }

            is MyExpenseCategoriesScreenState.Loading -> {
            }
        }
    }
}


@Composable
private fun MyExpenseCategoriesScreenContent(
    paddingValues: PaddingValues,
    screenState: MyExpenseCategoriesScreenState.Content,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        item {
            ListItemSearch(query = screenState.data.searchQuery)
            DefaultHorizontalDivider()
        }

        items(items = screenState.data.expenseCategories, key = { it.id }) {
            ListItemTransactionCategory(category = it)
            DefaultHorizontalDivider()
        }
    }
}