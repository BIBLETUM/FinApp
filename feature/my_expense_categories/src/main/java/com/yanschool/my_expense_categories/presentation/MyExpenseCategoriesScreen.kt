package com.yanschool.my_expense_categories.presentation

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
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yanschool.components.core.DefaultHorizontalDivider
import com.yanschool.components.core.DefaultTopAppBar
import com.yanschool.components.core.ErrorScreen
import com.yanschool.components.core.Loader
import com.yanschool.components.core.SearchBar
import com.yanschool.finapp.ui.R
import com.yanschool.my_expense_categories.presentation.components.ListItemTransactionCategory

@Composable
fun MyExpenseCategoriesScreenRoot(
    paddingValues: PaddingValues,
    viewModel: MyExpenseCategoriesViewModel = hiltViewModel()
) {
    val screenState = viewModel.screenState.collectAsStateWithLifecycle()

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
            .padding(bottom = paddingValues.calculateBottomPadding()),
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
                ErrorScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPaddingValues.calculateTopPadding())
                )
            }

            is MyExpenseCategoriesScreenState.Loading -> {
                Loader(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPaddingValues.calculateTopPadding())
                )
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
            .padding(top = paddingValues.calculateTopPadding()),
    ) {
        item {
            SearchBar(query = screenState.searchQuery)
            DefaultHorizontalDivider()
        }

        items(items = screenState.expenseCategories, key = { it.id }) {
            ListItemTransactionCategory(category = it)
            DefaultHorizontalDivider()
        }
    }
}
