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
import com.yanschool.utils.extensions.toNormalizedDecimalString

@Composable
fun BalanceAmountTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
) {
    val backgroundColor =
        if (isError) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.surface
    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .background(backgroundColor),
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
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = { newText ->
            onValueChange(newText.toNormalizedDecimalString())
        },
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.End,
        ),
        visualTransformation = IntegerOnlyVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
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

class IntegerOnlyVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val original = text.text
        val result = original.toNormalizedDecimalString()

        return TransformedText(
            AnnotatedString(result),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return result.length.coerceAtMost(offset)
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return original.length.coerceAtMost(offset)
                }
            }
        )
    }
}
