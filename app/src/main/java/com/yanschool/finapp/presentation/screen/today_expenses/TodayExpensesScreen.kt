package com.yanschool.finapp.presentation.screen.today_expenses

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
import com.yanschool.components.core.FloatingActionButtonPlus
import com.yanschool.components.core.Loader
import com.yanschool.components.core.TopBarHistoryActionIcon
import com.yanschool.finapp.presentation.components.ListItemTotalAccountChanges
import com.yanschool.finapp.ui.R

@Composable
fun TodayExpensesScreenRoot(
    paddingValues: PaddingValues,
    viewModel: TodayExpensesViewModel = hiltViewModel(),
    onHistoryClick: () -> Unit,
) {
    val screenState = viewModel.screenSate.collectAsStateWithLifecycle()

    TodayExpensesScreen(
        paddingValues = paddingValues,
        screenState = screenState,
        onHistoryClick = onHistoryClick
    )
}

@Composable
private fun TodayExpensesScreen(
    paddingValues: PaddingValues,
    screenState: State<TodayExpensesScreenState>,
    onHistoryClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            DefaultTopAppBar(titleRes = R.string.today_expenses, actions = {
                TopBarHistoryActionIcon() {
                    onHistoryClick()
                }
            })
        },
        floatingActionButton = {
            FloatingActionButtonPlus(
            )
        },
    ) { innerPaddingValues ->
        when (val currentState = screenState.value) {
            is TodayExpensesScreenState.Content -> {
                TodayExpensesScreenContent(
                    paddingValues = innerPaddingValues, screenState = currentState
                )
            }

            is TodayExpensesScreenState.Error -> {
                Log.d("TodayExpensesScreen", currentState.msg)
                ErrorScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPaddingValues.calculateTopPadding())
                )
            }

            is TodayExpensesScreenState.Loading -> {
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
private fun TodayExpensesScreenContent(
    paddingValues: PaddingValues,
    screenState: TodayExpensesScreenState.Content,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
    ) {
        item {
            ListItemTotalAccountChanges(amount = screenState.total)
            DefaultHorizontalDivider()
        }

        items(items = screenState.transactionShortUis, key = { it.id }) {
            ListItemExpense(transaction = it)
            DefaultHorizontalDivider()
        }
    }
}
