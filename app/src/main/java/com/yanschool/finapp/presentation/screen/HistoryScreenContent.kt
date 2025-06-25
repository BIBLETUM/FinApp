package com.yanschool.finapp.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.yanschool.components.core.DefaultHorizontalDivider
import com.yanschool.components.core.date_picker.DatePickerModal
import com.yanschool.components.core.date_picker.DateType
import com.yanschool.finapp.presentation.components.ListItemInfoContainer
import com.yanschool.finapp.presentation.components.ListItemTransactionHistory
import com.yanschool.finapp.ui.R

@Composable
fun HistoryScreenContent(
    paddingValues: PaddingValues,
    screenState: HistoryScreenStateContent,
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