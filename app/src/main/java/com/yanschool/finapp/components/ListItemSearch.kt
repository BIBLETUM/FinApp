package com.yanschool.finapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.yanschool.components.core.ListItem
import com.yanschool.ui.R

@Composable
fun ListItemSearch(
    query: String,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.surfaceContainerHigh),
        trailingAction = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.search_ic),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                contentDescription = null,
            )
        },
    ) {
        BasicTextField(
            value = query,
            onValueChange = {},
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyLarge,
            decorationBox = { _ ->
                if (query.isEmpty()) {
                    Text(
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        text = stringResource(com.yanschool.finapp.R.string.find_expense_category),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        )
    }
}