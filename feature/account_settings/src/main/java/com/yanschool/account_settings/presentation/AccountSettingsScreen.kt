package com.yanschool.account_settings.presentation

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yanschool.account_settings.presentation.components.BalanceAmountTextInput
import com.yanschool.account_settings.presentation.components.BalanceNameTextInput
import com.yanschool.account_settings.presentation.components.CurrencyBottomSheet
import com.yanschool.components.core.DefaultHorizontalDivider
import com.yanschool.components.core.DefaultTopAppBar
import com.yanschool.components.core.ErrorScreen
import com.yanschool.components.core.ListItemCurrency
import com.yanschool.components.core.Loader
import com.yanschool.finapp.ui.R

@Composable
fun AccountSettingsScreenRoot(
    paddingValues: PaddingValues,
    onDone: () -> Unit,
    viewModel: AccountSettingsViewModel = hiltViewModel()
) {
    val screenState = viewModel.screenState.collectAsStateWithLifecycle()

    AccountSettingsScreen(
        paddingValues = paddingValues,
        screenState = screenState,
        onDone = onDone,
        onAction = viewModel::onAction
    )
}

@Composable
private fun AccountSettingsScreen(
    paddingValues: PaddingValues,
    onDone: () -> Unit,
    onAction: (AccountSettingsScreenActions) -> Unit,
    screenState: State<AccountSettingsScreenState>,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            DefaultTopAppBar(
                titleRes = R.string.my_balance,
                navigationIcon = {
                    NavigationIcon(onClick = onDone)
                },
                actions = {
                    ActionIcon(onClick = { onAction(AccountSettingsScreenActions.SaveChanges) })
                }
            )
        },
    ) { innerPaddingValues ->
        when (val currentState = screenState.value) {
            is AccountSettingsScreenState.Content -> {
                AccountSettingsScreenContent(
                    paddingValues = innerPaddingValues,
                    onAction = onAction,
                    screenState = currentState,
                )
            }

            is AccountSettingsScreenState.Error -> {
                ErrorScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPaddingValues.calculateTopPadding())
                )
                Log.d("AccountSettingsScreen", currentState.msg)
            }

            is AccountSettingsScreenState.Loading -> {
                Loader(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPaddingValues.calculateTopPadding())
                )
            }

            is AccountSettingsScreenState.ChangesSaved -> {
                onDone()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AccountSettingsScreenContent(
    paddingValues: PaddingValues,
    onAction: (AccountSettingsScreenActions) -> Unit,
    screenState: AccountSettingsScreenState.Content,
) {
    val sheetState = rememberModalBottomSheetState()
    val showBottomSheet = remember { mutableStateOf(false) }

    LaunchedEffect(showBottomSheet.value) {
        when (showBottomSheet.value) {
            true -> sheetState.show()
            false -> sheetState.hide()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
    ) {
        BalanceNameTextInput(
            query = screenState.name,
            onQueryChange = { onAction(AccountSettingsScreenActions.SetNewName(it)) },
        )
        DefaultHorizontalDivider()
        BalanceAmountTextInput(
            value = screenState.balance,
            onValueChange = {
                onAction(AccountSettingsScreenActions.SetNewBalance(it))
            },
            currencySymbol = screenState.currency.symbol,
        )
        DefaultHorizontalDivider()
        ListItemCurrency(
            modifier = Modifier.clickable {
                showBottomSheet.value = true
            },
            backgroundColor = MaterialTheme.colorScheme.surface,
            currencySymbol = screenState.currency.symbol,
        )
        DefaultHorizontalDivider()
    }
    if (showBottomSheet.value) {
        CurrencyBottomSheet(
            onDismiss = {
                showBottomSheet.value = false
            },
            sheetState = sheetState,
            onCurrencySelected = {
                onAction(AccountSettingsScreenActions.SetNewCurrency(it))
                showBottomSheet.value = false
            },
        )
    }
}

@Composable
private fun NavigationIcon(
    onClick: () -> Unit,
) {
    Spacer(modifier = Modifier.width(4.dp))
    IconButton(
        onClick = { onClick() },
        modifier = Modifier.size(48.dp),
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.cross_ic),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            contentDescription = null,
        )
    }
}

@Composable
private fun RowScope.ActionIcon(
    onClick: () -> Unit,
) {
    IconButton(
        onClick = { onClick() },
        modifier = Modifier.size(48.dp),
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.check_ic),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            contentDescription = null,
        )
    }
    Spacer(modifier = Modifier.width(4.dp))
}
