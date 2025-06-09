package com.yanschool.components.core

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    trailingAction: (@Composable () -> Unit)? = null,
    mainContent: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            leadingContent?.let {
                it.invoke()
                Spacer(modifier = Modifier.width(16.dp))
            }
            mainContent()
            Spacer(modifier = Modifier.weight(1f))
            trailingContent?.let {
                Spacer(modifier = Modifier.width(16.dp))
                it.invoke()
            }
            trailingAction?.let {
                Spacer(modifier = Modifier.width(16.dp))
                it.invoke()
            }
            Spacer(modifier = Modifier.width(16.dp))
        }
    }

}