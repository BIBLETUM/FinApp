package com.yanschool.components.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultHorizontalDivider() {
    HorizontalDivider(
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.outlineVariant
    )
}
