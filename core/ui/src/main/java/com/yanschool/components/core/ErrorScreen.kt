package com.yanschool.components.core

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.yanschool.finapp.ui.R

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String = stringResource(R.string.an_unexpected_error_occurred_try_restarting_the_application),
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
