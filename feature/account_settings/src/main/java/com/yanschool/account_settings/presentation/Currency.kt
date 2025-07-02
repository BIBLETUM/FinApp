package com.yanschool.account_settings.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.yanschool.finapp.ui.R

enum class Currency(
    @StringRes val captionResId: Int,
    @DrawableRes val iconResId: Int,
    val symbol: String,
) {
    RUB(
        captionResId = R.string.russian_ruble,
        iconResId = R.drawable.ruble_ic,
        symbol = "₽"
    ),
    USD(
        captionResId = R.string.us_dollar,
        iconResId = R.drawable.dollar_ic,
        symbol = "$",
    ),
    EUR(
        captionResId = R.string.euro,
        iconResId = R.drawable.euro_ic,
        symbol = "€",
    ),
}
