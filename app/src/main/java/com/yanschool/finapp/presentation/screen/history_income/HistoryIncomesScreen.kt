package com.yanschool.finapp.presentation.screen.history_income

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yanschool.components.core.DefaultHorizontalDivider
import com.yanschool.components.core.DefaultTopAppBar
import com.yanschool.components.core.ErrorScreen
import com.yanschool.components.core.Loader
import com.yanschool.components.core.date_picker.DatePickerModal
import com.yanschool.components.core.date_picker.DateType
import com.yanschool.finapp.R
import com.yanschool.finapp.presentation.components.ListItemInfoContainer
import com.yanschool.finapp.presentation.components.ListItemTransactionHistory

@Composable
fun HistoryIncomesScreenRoot(
    paddingValues: PaddingValues,
    viewModel: HistoryIncomesScreenViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val screenState = viewModel.screenState.collectAsStateWithLifecycle()

    HistoryIncomesScreen(
        paddingValues = paddingValues,
        screenState = screenState,
        onNavigateBack = navigateBack,
        onDateSelected = { date, type ->
            viewModel.selectDate(date, type)
        }
    )
}

@Composable
private fun HistoryIncomesScreen(
    paddingValues: PaddingValues,
    onNavigateBack: () -> Unit,
    onDateSelected: (Long?, DateType) -> Unit,
    screenState: State<HistoryIncomesScreenState>,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            DefaultTopAppBar(
                titleRes = R.string.my_history,
                actions = { TopBarActionIcon() },
                navigationIcon = {
                    TopBarNavigationIcon(
                        onClick = onNavigateBack
                    )
                })
        },
    ) { innerPaddingValues ->
        when (val currentState = screenState.value) {
            is HistoryIncomesScreenState.Content -> {
                HistoryIncomesScreenContent(
                    paddingValues = innerPaddingValues,
                    screenState = currentState,
                    onDateSelected = onDateSelected,
                )
            }

            is HistoryIncomesScreenState.Error -> {
                Log.d("HistoryIncomesScreen", currentState.msg)
                ErrorScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPaddingValues.calculateTopPadding())
                )
            }

            is HistoryIncomesScreenState.Loading -> {
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
private fun HistoryIncomesScreenContent(
    paddingValues: PaddingValues,
    screenState: HistoryIncomesScreenState.Content,
    onDateSelected: (Long?, DateType) -> Unit,
) {
    val isDatePickerEnabled = remember {
        mutableStateOf(false)
    }
    val selectedDateType = remember { mutableStateOf<DateType?>(null) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
    ) {
        item {
            ListItemInfoContainer(
                modifier = Modifier.clickable {
                    isDatePickerEnabled.value = true
                    selectedDateType.value = DateType.START
                },
                leadingText = stringResource(R.string.beginning),
                trailingText = screenState.startDate,
            )
            DefaultHorizontalDivider()
            ListItemInfoContainer(
                modifier = Modifier.clickable {
                    isDatePickerEnabled.value = true
                    selectedDateType.value = DateType.END
                },
                leadingText = stringResource(R.string.end),
                trailingText = screenState.endDate,
            )
            DefaultHorizontalDivider()
            ListItemInfoContainer(
                leadingText = stringResource(R.string.sum),
                trailingText = screenState.total,
            )
        }
        items(items = screenState.transactions, key = { it.id }) {
            ListItemTransactionHistory(transaction = it)
            DefaultHorizontalDivider()
        }
    }

    if (isDatePickerEnabled.value) {
        selectedDateType.value?.let { currentType ->
            DatePickerModal(
                dateType = currentType,
                onDateSelected = { dateMillis, dateType ->
                    onDateSelected(dateMillis, dateType)
                    isDatePickerEnabled.value = false
                    selectedDateType.value = null
                },
                onDismiss = {
                    isDatePickerEnabled.value = false
                    selectedDateType.value = null
                }
            )
        }
    }
}

@Composable
private fun RowScope.TopBarActionIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(48.dp),
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(com.yanschool.ui.R.drawable.history_outline_ic),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            contentDescription = null,
        )
    }
}

@Composable
private fun TopBarNavigationIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(48.dp),
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(com.yanschool.ui.R.drawable.arrow_back_ic),
            tint = MaterialTheme.colorScheme.onSurface,
            contentDescription = null,
        )
    }
}


