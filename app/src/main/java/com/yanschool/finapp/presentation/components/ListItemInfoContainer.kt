package com.yanschool.finapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yanschool.components.core.ListItem

@Composable
fun ListItemInfoContainer(
    leadingText: String,
    trailingText: String,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier
            .heightIn(56.dp)
            .background(MaterialTheme.colorScheme.primaryContainer),
        trailingContent = {
            Text(
                text = trailingText,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    ) {
        Text(
            text = leadingText,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}