package com.yanschool.components.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmojiWithCircle(
    modifier: Modifier = Modifier,
    emoji: String,
    size: Dp = 24.dp,
    backgroundColor: Color = MaterialTheme
        .colorScheme.primaryContainer,
) {
    Box(
        modifier = modifier
            .background(backgroundColor, shape = CircleShape)
            .sizeIn(minWidth = size, minHeight = size),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = emoji,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}
