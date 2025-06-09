package com.yanschool.finapp.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yanschool.components.core.DefaultHorizontalDivider
import com.yanschool.components.core.DefaultTopAppBar
import com.yanschool.components.core.FloatingActionButtonPlus
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
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        topBar = {
            DefaultTopAppBar(
                titleRes = R.string.today_incomes,
                actions = {
                    Box(Modifier.size(48.dp), contentAlignment = Alignment.Center) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(com.yanschool.ui.R.drawable.history_ic),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            contentDescription = null,
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                }
            )
        },
        floatingActionButton = {
            FloatingActionButtonPlus(
            )
        },
    ) { innerPaddingValues ->
        TodayExpensesScreenContent(
            paddingValues = innerPaddingValues,
            todayExpenses = DailyTransactionGroupUi(
                total = "600 000 ₽",
                transactionShortUis = Mocks.mockTransactionsIncome,
                type = TransactionTypeUi.INCOME,
            )
        )
    }
}

@Composable
private fun TodayExpensesScreenContent(
    paddingValues: PaddingValues,
    todayExpenses: DailyTransactionGroupUi,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        item {
            ListItemTotalAccountChanges(
                amount = todayExpenses.total,
            )
            DefaultHorizontalDivider()
        }

        items(items = todayExpenses.transactionShortUis, key = { it.id }) {
            ListItemIncome(transaction = it)
            DefaultHorizontalDivider()
        }
    }
}