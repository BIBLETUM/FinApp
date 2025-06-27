package com.yanschool.finapp.presentation.components.navigation

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun NavigationBarItemLabel(
    isSelected: Boolean,
    @StringRes titleResId: Int,
) {
    val textStyle = when (isSelected) {
        true -> MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.SemiBold
        )

        false -> MaterialTheme.typography.labelMedium
    }
    Text(
        text = stringResource(id = titleResId),
        style = textStyle,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}
