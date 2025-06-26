package com.yanschool.utils.extensions

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Преобразует значение [BigDecimal] в строку с символом валюты и разделением тысяч пробелами.
 *
 * Значение округляется вниз до целого числа (без дробной части), после чего форматируется:
 * - Цифры разбиваются на группы по 3 символа с конца
 * - Между группами вставляются пробелы
 * - В конце добавляется символ валюты
 *
 * Пример:
 * ```
 * BigDecimal("1234567.89").toStringWithCurrency("₽") // "1 234 567 ₽"
 * ```
 *
 * @param currencySymbol символ валюты, по умолчанию "₽"
 * @return отформатированная строка, представляющая сумму с символом валюты
 */
fun BigDecimal.toStringWithCurrency(
    currencySymbol: String = "₽"
): String {
    val parts = this.setScale(0, RoundingMode.DOWN)
        .toPlainString()
        .reversed()
        .chunked(3)
        .joinToString(" ")
        .reversed()

    return "$parts $currencySymbol"
}