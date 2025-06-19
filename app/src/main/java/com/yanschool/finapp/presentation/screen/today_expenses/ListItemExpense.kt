package com.yanschool.finapp.presentation.screen.today_expenses

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.yanschool.components.core.EmojiOrLiteralsWithCircle
import com.yanschool.components.core.ListItem
import com.yanschool.finapp.presentation.model.TransactionShortUi
import com.yanschool.ui.R

@Composable
fun ListItemExpense(
    transaction: TransactionShortUi,
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
        ListItemExpenseWithDescription(
            name = transaction.category.name,
            description = transaction.comment,
        )
    }
}

@Composable
private fun ListItemExpenseWithDescription(
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
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}