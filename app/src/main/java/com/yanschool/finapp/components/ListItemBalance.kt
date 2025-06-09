package com.yanschool.finapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yanschool.components.core.EmojiWithCircle
import com.yanschool.components.core.ListItem
import com.yanschool.finapp.R

@Composable
fun ListItemBalance(
    amount: String,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.primaryContainer),
        leadingContent = {
            EmojiWithCircle(
                emoji = stringResource(R.string.money_bag_emoji),
                size = 24.dp,
                backgroundColor = MaterialTheme.colorScheme.onPrimary
            )
        },
        trailingContent = {
            Text(
                text = amount,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        },
        trailingAction = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(com.yanschool.ui.R.drawable.more_vert),
                tint = MaterialTheme.colorScheme.tertiary,
                contentDescription = null,
            )
        },
    ) {
        Text(
            text = stringResource(R.string.balance),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}