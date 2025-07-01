package com.yanschool.utils.extensions

fun String.getCurrencySymbol(): String {
    return when (this) {
        RUBBLES -> "₽"
        EURO -> "€"
        DOLLAR -> "$"
        else -> this
    }
}

const val RUBBLES = "RUB"
const val EURO = "EUR"
const val DOLLAR = "USD"
