package com.yanschool.finapp.screen.today_expenses

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
import com.yanschool.components.core.FloatingActionButtonPlus
import com.yanschool.components.core.TopBarHistoryActionIcon
import com.yanschool.finapp.R
import com.yanschool.finapp.components.ListItemExpense
import com.yanschool.finapp.components.ListItemTotalAccountChanges
import com.yanschool.finapp.mock.Mocks
import com.yanschool.finapp.model.DailyTransactionGroupUi
import com.yanschool.finapp.model.TransactionTypeUi

@Composable
fun TodayExpensesScreenRoot(
    paddingValues: PaddingValues,
) {
    val screenState = remember {
        mutableStateOf(
            TodayExpensesScreenState.Content(
                data = DailyTransactionGroupUi(
                    total = "436 558 ₽",
                    transactionShortUis = Mocks.mockTransactionsExpense,
                    type = TransactionTypeUi.EXPENSE
                )
            )
        )
    }
    TodayExpensesScreen(paddingValues = paddingValues, screenState = screenState)
}

@Composable
private fun TodayExpensesScreen(
    paddingValues: PaddingValues,
    screenState: State<TodayExpensesScreenState>,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            DefaultTopAppBar(titleRes = R.string.today_expenses, actions = {
                TopBarHistoryActionIcon()
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
            }

            is TodayExpensesScreenState.Loading -> {}
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
            .padding(paddingValues),
    ) {
        item {
            ListItemTotalAccountChanges(amount = screenState.data.total)
            DefaultHorizontalDivider()
        }

        items(items = screenState.data.transactionShortUis, key = { it.id }) {
            ListItemExpense(transaction = it)
            DefaultHorizontalDivider()
        }
    }
}
