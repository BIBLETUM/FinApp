package com.yanschool.finapp.presentation.screen.account_balance

import com.yanschool.core.extensions.toStringWithCurrency
import com.yanschool.finapp.domain.account_info.AccountInfo
import com.yanschool.finapp.presentation.model.AccountBalanceUi
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