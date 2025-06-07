package com.yanschool.finapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yanschool.components.core.ListItem
import com.yanschool.ui.R
import com.yanschool.ui.theme.FinAppTheme
import com.yanschool.ui.theme.Literals


@Preview
@Composable
fun Aboba() {
    FinAppTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            ListItemExpense(
                name = "Аренда квартиры",
                amount = "100 000 ₽",
                expenseLeadingContent = ListItemLeadingPreviewContent.Literals("РК"),
            )
            ListItemExpense(
                name = "Аренда квартиры",
                amount = "100 000 ₽",
                expenseLeadingContent = ListItemLeadingPreviewContent.Emoji("\uD83D\uDC36"),
            )
            ListItemExpense(
                name = "Аренда квартиры",
                amount = "100 000 ₽",
                expenseLeadingContent = ListItemLeadingPreviewContent.Literals("РК"),
            )
            ListItemExpense(
                name = "Аренда квартиры",
                amount = "100 000 ₽",
                expenseLeadingContent = ListItemLeadingPreviewContent.Literals("РК"),
            )
            ListItemExpense(
                name = "Аренда квартиры",
                amount = "100 000 ₽",
                expenseLeadingContent = ListItemLeadingPreviewContent.Literals("РК"),
            )
        }
    }
}

@Composable
fun ListItemExpense(
    name: String,
    amount: String,
    expenseLeadingContent: ListItemLeadingPreviewContent,
    modifier: Modifier = Modifier,
    description: String? = null,
    onClick: () -> Unit = {},
) {
    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable { onClick() },
        leadingContent = {
            EmojiOrLiteralsWithCircle(
                expenseLeadingContent
            )
        },
        trailingContent = {
            Text(
                text = amount,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        trailingAction = {
            Icon(
                painter = painterResource(R.drawable.more_vert),
                tint = MaterialTheme.colorScheme.tertiary,
                contentDescription = null,
            )
        },
    ) {
        ListItemExpenseWithDescription(
            name = name,
            description = description,
        )
    }
}

@Composable
fun EmojiOrLiteralsWithCircle(
    leadingContent: ListItemLeadingPreviewContent,
    size: Dp = 24.dp,
    backgroundColor: Color = MaterialTheme
        .colorScheme.primaryContainer,
) {
    Box(
        modifier = Modifier
            .background(backgroundColor, shape = CircleShape)
            .size(size),
        contentAlignment = Alignment.Center
    ) {
        when (leadingContent) {
            is ListItemLeadingPreviewContent.Literals -> {
                Text(
                    text = leadingContent.value,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 10.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = Literals
                )
            }
            is ListItemLeadingPreviewContent.Emoji -> {
                Text(
                    text = leadingContent.value,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }

    }
}


@Composable
private fun ListItemExpenseWithDescription(
    name: String,
    description: String? = null,
) {
    Column {
        Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
        description?.let {
            Text(
                text = it,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }
}