@file:Suppress("TooGenericExceptionCaught", "SwallowedException")

package com.yanschool.utils.extensions

fun String.getCurrencySymbol(): String {
    return when (this) {
        RUBBLES -> RUBBLES_SYMBOL
        EURO -> EURO_SYMBOL
        DOLLAR -> DOLLAR_SYMBOL
        else -> this
    }
}

fun String.getCurrencyName(): String {
    return when (this) {
        RUBBLES_SYMBOL -> RUBBLES
        EURO_SYMBOL -> EURO
        DOLLAR_SYMBOL -> DOLLAR
        else -> this
    }
}

/**
 * Извлекает из строки числовое значение, допускающее:
 * - один ведущий минус (для отрицательных чисел),
 * - одну десятичную точку (или запятую, которая будет преобразована в точку),
 * - цифры от 0 до 9.
 *
 * При этом:
 * - все лишние символы, кроме цифр, одного минуса и одной десятичной точки, удаляются,
 * - множественные минусы заменяются на один ведущий минус,
 * - запятые заменяются на точку и учитывается только первая десятичная точка,
 * - пробелы в начале строки игнорируются.
 *
 * Пример:
 * ```
 * "-12-3,45abc" -> "-123.45"
 * "  98,7.6" -> "98.76"
 * "abc-123" -> "-123"
 * ```
 *
 * @return строку, содержащую корректное числовое значение с десятичной точкой в формате для парсинга
 * в число с плавающей точкой.
 */
fun String.toNormalizedDecimalString(): String {
    val trimmed = this.trimStart()
    val hasLeadingMinus = trimmed.startsWith('-')

    val withoutDuplicateMinus = if (hasLeadingMinus) {
        "-" + trimmed.removePrefix("-").replace("-", "")
    } else {
        trimmed.replace("-", "")
    }

    val digitsWithSeparators = withoutDuplicateMinus.replace(Regex("[^\\d.,]"), "")

    val cleaned = buildString {
        var dotAdded = false
        for (ch in digitsWithSeparators) {
            when (ch) {
                '.' -> if (!dotAdded) {
                    append('.')
                    dotAdded = true
                }
                ',' -> if (!dotAdded) {
                    append('.')
                    dotAdded = true
                }
                in '0'..'9' -> append(ch)
            }
        }
    }

    return if (hasLeadingMinus) "-$cleaned" else cleaned
}

private const val RUBBLES = "RUB"
private const val EURO = "EUR"
private const val DOLLAR = "USD"

private const val RUBBLES_SYMBOL = "₽"
private const val EURO_SYMBOL = "€"
private const val DOLLAR_SYMBOL = "$"
