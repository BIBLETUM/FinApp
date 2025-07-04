package com.yanschool.components.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yanschool.finapp.ui.R

@Composable
fun ListItemCurrency(
    currencySymbol: String,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .background(backgroundColor),
        trailingContent = {
            Text(
                text = currencySymbol,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        },
        trailingAction = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.more_vert),
                tint = MaterialTheme.colorScheme.tertiary,
                contentDescription = null,
            )
        },
    ) {
        Text(
            text = stringResource(R.string.currency),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}
