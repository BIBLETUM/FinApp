package com.yanschool.account_info.presentation

import com.yanschool.domain.common_models.AccountInfo
import com.yanschool.utils.extensions.toStringWithCurrency
import java.math.BigDecimal
import javax.inject.Inject

class AccountInfoMapper @Inject constructor() {

    fun mapDomainToUi(domain: AccountInfo): AccountBalanceUi {
        val currencySymbol = when (domain.currency) {
            RUBBLES -> "₽"
            EURO -> "€"
            DOLLAR -> "$"
            else -> domain.currency
        }

        return AccountBalanceUi(
            amount = BigDecimal(domain.balance).toStringWithCurrency(
                currencySymbol
            ),
            currency = currencySymbol,
        )
    }

    private companion object {
        const val RUBBLES = "RUB"
        const val EURO = "EUR"
        const val DOLLAR = "USD"
    }
}
