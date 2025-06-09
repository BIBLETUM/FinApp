package com.yanschool.finapp.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.yanschool.finapp.components.ListItemBalance
import com.yanschool.finapp.components.ListItemCurrency
import com.yanschool.finapp.model.AccountBalanceUi

@Composable
fun AccountBalanceScreen(
    paddingValues: PaddingValues,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        topBar = {
            DefaultTopAppBar(
                titleRes = R.string.my_balance,
                actions = {
                    Box(Modifier.size(48.dp), contentAlignment = Alignment.Center) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(com.yanschool.ui.R.drawable.edit_ic),
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
        AccountBalanceScreenContent(
            paddingValues = innerPaddingValues,
            accountBalance = AccountBalanceUi(
                amount = "-670 000 ₽",
                currency = "₽",
            )
        )
    }
}

@Composable
private fun AccountBalanceScreenContent(
    paddingValues: PaddingValues,
    accountBalance: AccountBalanceUi,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        ListItemBalance(
            amount = accountBalance.amount
        )
        DefaultHorizontalDivider()
        ListItemCurrency(
            currency = accountBalance.currency
        )
    }
}