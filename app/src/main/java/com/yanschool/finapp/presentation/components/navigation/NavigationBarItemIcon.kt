package com.yanschool.finapp.presentation.components.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun NavigationBarItemIcon(
    @DrawableRes iconResId: Int,
    @StringRes titleResId: Int,
) {
    Icon(
        modifier = Modifier.size(24.dp),
        painter = painterResource(id = iconResId),
        contentDescription = stringResource(id = titleResId)
    )
}
