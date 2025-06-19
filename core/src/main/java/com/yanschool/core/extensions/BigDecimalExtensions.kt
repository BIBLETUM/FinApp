package com.yanschool.core.extensions

import java.math.BigDecimal
import java.math.RoundingMode

fun BigDecimal.toStringWithCurrency(): String {
    val parts = this.setScale(0, RoundingMode.DOWN)
        .toPlainString()
        .reversed()
        .chunked(3)
        .joinToString(" ")
        .reversed()

    return "$parts ₽"
}