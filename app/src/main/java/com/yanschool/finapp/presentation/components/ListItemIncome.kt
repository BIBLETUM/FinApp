package com.yanschool.finapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.yanschool.components.core.ListItem
import com.yanschool.finapp.presentation.model.TransactionShortUi
import com.yanschool.ui.R

@Composable
fun ListItemIncome(
    transaction: TransactionShortUi,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(72.dp)
            .clickable { onClick() },
        trailingContent = {
            Text(
                text = transaction.amount,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge,
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
            text = transaction.category.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}