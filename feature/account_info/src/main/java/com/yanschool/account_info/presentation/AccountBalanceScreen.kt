package com.yanschool.account_info.presentation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yanschool.account_info.presentation.components.ListItemBalance
import com.yanschool.components.core.DefaultHorizontalDivider
import com.yanschool.components.core.DefaultTopAppBar
import com.yanschool.components.core.ErrorScreen
import com.yanschool.components.core.FloatingActionButtonPlus
import com.yanschool.components.core.ListItemCurrency
import com.yanschool.components.core.Loader
import com.yanschool.finapp.ui.R

@Composable
fun AccountBalanceScreenRoot(
    paddingValues: PaddingValues,
    onEditClick: () -> Unit,
    viewModel: AccountBalanceViewModel = hiltViewModel()
) {
    val screenState = viewModel.screenSate.collectAsStateWithLifecycle()

    AccountBalanceScreen(
        paddingValues = paddingValues,
        screenState = screenState,
        onEditClick = onEditClick
    )
}

@Composable
private fun AccountBalanceScreen(
    paddingValues: PaddingValues,
    onEditClick: () -> Unit,
    screenState: State<AccountBalanceScreenState>,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            DefaultTopAppBar(
                titleRes = R.string.my_balance,
                actions = {
                    IconButton(
                        onClick = { onEditClick() },
                        modifier = Modifier.size(48.dp),
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(R.drawable.edit_ic),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            contentDescription = null,
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                }
            )
        },
        floatingActionButton = {
            FloatingActionButtonPlus()
        },
    ) { innerPaddingValues ->
        when (val currentState = screenState.value) {
            is AccountBalanceScreenState.Content -> {
                AccountBalanceScreenContent(
                    paddingValues = innerPaddingValues,
                    screenState = currentState
                )
            }

            is AccountBalanceScreenState.Error -> {
                ErrorScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPaddingValues.calculateTopPadding())
                )
                Log.d("AccountBalanceScreen", currentState.msg)
            }

            is AccountBalanceScreenState.Loading -> {
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
private fun AccountBalanceScreenContent(
    paddingValues: PaddingValues,
    screenState: AccountBalanceScreenState.Content,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
    ) {
        ListItemBalance(
            amount = screenState.data.amount
        )
        DefaultHorizontalDivider()
        ListItemCurrency(
            currencySymbol = screenState.data.currencySymbol
        )
    }
}
