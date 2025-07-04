package com.yanschool.account_settings.presentation.components

import androidx.compose.foundation.background
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
import com.yanschool.components.core.ListItem
import com.yanschool.finapp.ui.R

@Composable
fun CancelItemButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier
            .heightIn(72.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.error)
            .clickable { onClick() },
        leadingContent = {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(R.drawable.cancel_ic),
                contentDescription = stringResource(R.string.cancel),
                tint = MaterialTheme.colorScheme.surfaceContainerLowest
            )
        }
    ) {
        Text(
            text = stringResource(R.string.cancel),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}
