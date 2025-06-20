package com.yanschool.finapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yanschool.components.core.ListItem
import com.yanschool.finapp.R

@Composable
fun ListItemTotalAccountChanges(
    amount: String,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier
            .heightIn(56.dp)
            .background(MaterialTheme.colorScheme.primaryContainer),
        trailingContent = {
            Text(
                text = amount,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    ) {
        Text(
            text = stringResource(R.string.total),
            style = MaterialTheme.typography.bodyLarge.copy(
                lineHeight = 24.sp,
                fontSize = 16.sp,
                letterSpacing = 0.5.sp,
            ),
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}