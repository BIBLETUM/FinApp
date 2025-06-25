package com.yanschool.components.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.yanschool.common_models.TransactionDetailUi
import com.yanschool.finapp.ui.R

@Composable
fun ListItemTransactionHistory(
    transaction: TransactionDetailUi,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(70.dp)
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onClick() },
        leadingContent = {
            EmojiOrLiteralsWithCircle(
                literals = transaction.category.literals,
                emoji = transaction.category.emoji,
            )
        },
        trailingContent = {
            ListItemTransactionAmountWithTime(
                amount = transaction.amount,
                time = transaction.time,
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
        ListItemTransactionNameWithDescription(
            name = transaction.category.name,
            description = transaction.comment,
        )
    }
}

@Composable
private fun ListItemTransactionNameWithDescription(
    name: String,
    description: String? = null,
) {
    Column {
        Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
        description?.let {
            Text(
                text = it,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun ListItemTransactionAmountWithTime(
    amount: String,
    time: String,
) {
    Text(
        text = "$amount\n$time",
        maxLines = 2,
        textAlign = TextAlign.End,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface,
    )
}