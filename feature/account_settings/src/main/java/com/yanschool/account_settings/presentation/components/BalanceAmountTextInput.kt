package com.yanschool.account_settings.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.yanschool.components.core.EmojiWithCircle
import com.yanschool.components.core.ListItem
import com.yanschool.finapp.ui.R
import com.yanschool.utils.extensions.formatAmountWithSpaces

@Composable
fun BalanceAmountTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    currencySymbol: String,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .background(MaterialTheme.colorScheme.surface),
        leadingContent = {
            EmojiWithCircle(
                emoji = stringResource(R.string.money_bag_emoji),
                size = 24.dp,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer
            )
        },
        trailingContent = {
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.weight(1f),
                currencySymbol = currencySymbol,
            )
        },
    ) {
        Text(
            text = stringResource(R.string.balance),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
private fun TextField(
    value: String,
    currencySymbol: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = { newText ->
            val filtered = newText.filter { it.isDigit() }
            onValueChange(filtered)
        },
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.End
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        visualTransformation = CurrencyVisualTransformation(currencySymbol),
        decorationBox = { innerTextField ->
            Box(contentAlignment = Alignment.CenterEnd) {
                if (value.isEmpty()) {
                    Text(
                        text = stringResource(R.string.balance_value),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        textAlign = TextAlign.End,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                innerTextField()
            }
        }
    )
}

private class CurrencyVisualTransformation(
    private val currencySymbol: String
) : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val raw = text.text

        if (raw.isEmpty()) {
            return TransformedText(AnnotatedString(""), OffsetMapping.Identity)
        }

        val formatted = raw.formatAmountWithSpaces()
        val transformed = "$formatted $currencySymbol"

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                var count = 0
                var transformedOffset = 0
                for (i in formatted.indices) {
                    if (formatted[i].isDigit()) {
                        if (count == offset) break
                        count++
                    }
                    transformedOffset++
                }
                return transformedOffset
            }

            override fun transformedToOriginal(offset: Int): Int {
                var count = 0
                for (i in 0 until offset.coerceAtMost(formatted.length)) {
                    if (formatted[i].isDigit()) {
                        count++
                    }
                }
                return count
            }
        }

        return TransformedText(AnnotatedString(transformed), offsetMapping)
    }
}
