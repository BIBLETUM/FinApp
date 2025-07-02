package com.yanschool.account_settings.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.yanschool.account_settings.presentation.Currency
import com.yanschool.components.core.ListItem

@Composable
fun CurrencyItem(
    currency: Currency,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier
            .heightIn(72.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        leadingContent = {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(currency.iconResId),
                contentDescription = currency.name,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    ) {
        Text(
            text = stringResource(currency.captionResId),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}
