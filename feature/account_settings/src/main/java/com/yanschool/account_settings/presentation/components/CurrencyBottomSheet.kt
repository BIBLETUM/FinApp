package com.yanschool.account_settings.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yanschool.account_settings.presentation.Currency
import com.yanschool.components.core.DefaultHorizontalDivider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyBottomSheet(
    onDismiss: () -> Unit,
    onCurrencySelected: (Currency) -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(),
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismiss() },
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Currency.entries.forEach { currency ->
                CurrencyItem(
                    currency = currency,
                    onClick = {
                        onCurrencySelected(currency)
                    }
                )
                DefaultHorizontalDivider()
            }
            CancelItemButton(
                onClick = { onDismiss() }
            )
        }
    }
}
