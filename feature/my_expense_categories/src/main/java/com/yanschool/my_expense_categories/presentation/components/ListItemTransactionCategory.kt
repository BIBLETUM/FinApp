package com.yanschool.my_expense_categories.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.yanschool.common_models.TransactionCategoryUi
import com.yanschool.components.core.EmojiOrLiteralsWithCircle
import com.yanschool.components.core.ListItem

@Composable
fun ListItemTransactionCategory(
    category: TransactionCategoryUi,
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
                literals = category.literals,
                emoji = category.emoji,
            )
        },
    ) {
        Text(
            text = category.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}