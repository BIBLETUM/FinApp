package com.yanschool.finapp.screen.today_incomes

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
import com.yanschool.finapp.components.ListItemIncome
import com.yanschool.finapp.components.ListItemTotalAccountChanges
import com.yanschool.finapp.mock.Mocks
import com.yanschool.finapp.model.DailyTransactionGroupUi
import com.yanschool.finapp.model.TransactionTypeUi

@Composable
fun TodayIncomesScreenRoot(
    paddingValues: PaddingValues = PaddingValues(),
) {
    val screenState = remember {
        mutableStateOf(
            TodayIncomesScreenState.Content(
                data = DailyTransactionGroupUi(
                    total = "600 000 ₽",
                    transactionShortUis = Mocks.mockTransactionsIncome,
                    type = TransactionTypeUi.INCOME,
                )
            )
        )
    }

    TodayIncomesScreen(paddingValues = paddingValues, screenState = screenState)
}

@Composable
private fun TodayIncomesScreen(
    paddingValues: PaddingValues,
    screenState: State<TodayIncomesScreenState>,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            DefaultTopAppBar(
                titleRes = R.string.today_incomes,
                actions = {
                    TopBarHistoryActionIcon()
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
                Log.d("TodayExpensesScreen", currentState.msg)
            }

            is TodayIncomesScreenState.Loading -> {
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
            .padding(paddingValues),
    ) {
        item {
            ListItemTotalAccountChanges(
                amount = screenState.data.total,
            )
            DefaultHorizontalDivider()
        }

        items(items = screenState.data.transactionShortUis, key = { it.id }) {
            ListItemIncome(transaction = it)
            DefaultHorizontalDivider()
        }
    }
}