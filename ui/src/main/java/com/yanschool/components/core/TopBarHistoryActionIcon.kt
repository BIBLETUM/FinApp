package com.yanschool.components.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yanschool.ui.R

@Composable
fun RowScope.TopBarHistoryActionIcon() {
    Box(Modifier.size(48.dp), contentAlignment = Alignment.Center) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.history_ic),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            contentDescription = null,
        )
    }
}