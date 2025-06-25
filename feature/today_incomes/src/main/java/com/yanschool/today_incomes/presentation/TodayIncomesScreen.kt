package com.yanschool.today_incomes.presentation

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
import com.yanschool.components.core.ListItemTotalAccountChanges
import com.yanschool.components.core.Loader
import com.yanschool.components.core.TopBarHistoryActionIcon
import com.yanschool.finapp.ui.R

@Composable
fun TodayIncomesScreenRoot(
    paddingValues: PaddingValues = PaddingValues(),
    viewModel: TodayIncomesViewModel = hiltViewModel(),
    onHistoryClick: () -> Unit,
) {
    val screenState = viewModel.screenSate.collectAsStateWithLifecycle()

    TodayIncomesScreen(
        paddingValues = paddingValues,
        screenState = screenState,
        onHistoryClick = onHistoryClick
    )
}

@Composable
private fun TodayIncomesScreen(
    paddingValues: PaddingValues,
    screenState: State<TodayIncomesScreenState>,
    onHistoryClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            DefaultTopAppBar(
                titleRes = R.string.today_incomes,
                actions = {
                    TopBarHistoryActionIcon() {
                        onHistoryClick()
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButtonPlus(
            )
        },
    ) { innerPaddingValues ->
        when (val currentState = screenState.value) {
            is TodayIncomesScreenState.Content -> {
                TodayIncomesScreenContent(
                    paddingValues = innerPaddingValues,
                    screenState = currentState
                )
            }

            is TodayIncomesScreenState.Error -> {
                ErrorScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPaddingValues.calculateTopPadding())
                )
                Log.d("TodayExpensesScreen", currentState.msg)
            }

            is TodayIncomesScreenState.Loading -> {
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
private fun TodayIncomesScreenContent(
    paddingValues: PaddingValues,
    screenState: TodayIncomesScreenState.Content,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
    ) {
        item {
            ListItemTotalAccountChanges(
                amount = screenState.total,
            )
            DefaultHorizontalDivider()
        }

        items(items = screenState.transactionShortUis, key = { it.id }) {
            ListItemIncome(transaction = it)
            DefaultHorizontalDivider()
        }
    }
}