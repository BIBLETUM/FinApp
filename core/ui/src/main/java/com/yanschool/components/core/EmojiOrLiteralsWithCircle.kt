package com.yanschool.components.core

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun EmojiOrLiteralsWithCircle(
    literals: String,
    modifier: Modifier = Modifier,
    emoji: String? = null,
    size: Dp = 24.dp,
    backgroundColor: Color = MaterialTheme
        .colorScheme.primaryContainer,
) {
    when {
        emoji != null -> {
            EmojiWithCircle(
                modifier = modifier,
                emoji = emoji,
                size = size,
                backgroundColor = backgroundColor
            )
        }

        else -> {
            LiteralsWithCircle(
                literals = literals,
                modifier = modifier,
                size = size,
                backgroundColor = backgroundColor
            )
        }
    }
}
