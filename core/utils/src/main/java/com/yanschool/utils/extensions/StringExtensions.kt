@file:Suppress("TooGenericExceptionCaught", "SwallowedException")

package com.yanschool.utils.extensions

import java.math.RoundingMode

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
 * Очищает строку от всего, кроме цифр до первой точки или запятой.
 * Удаляет пробелы, валюты и дробные части.
 *
 * Примеры:
 * "123 456,78 ₽" -> "123456"
 * "1.234.567,89₽" -> "1234567"
 * "abc12 345.67 ₽" -> "12345"
 */
fun String.extractIntegerDigits(): String {
    val cleaned = this.replace(Regex("[^\\d.,]"), "")

    val integerPart = cleaned.split('.', ',').firstOrNull().orEmpty()

    return integerPart
}

/**
 * Отбрасывает дробную часть и добавляет пробелы как разделители тысяч
 *
 * Пример:
 * "500000.00" -> "500 000"
 */
fun String.formatAmountWithSpaces(): String {
    return try {
        val cleanString = this.replace("\\s".toRegex(), "")

        val wholeNumber = cleanString
            .toBigDecimal()
            .setScale(0, RoundingMode.DOWN)

        "%,d".format(wholeNumber.toBigInteger())
            .replace(',', ' ')
    } catch (_: Exception) {
        "0"
    }
}

private const val RUBBLES = "RUB"
private const val EURO = "EUR"
private const val DOLLAR = "USD"

private const val RUBBLES_SYMBOL = "₽"
private const val EURO_SYMBOL = "€"
private const val DOLLAR_SYMBOL = "$"
