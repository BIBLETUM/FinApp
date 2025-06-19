package com.yanschool.finapp.presentation.screen.account_balance

import com.yanschool.core.extensions.toStringWithCurrency
import com.yanschool.finapp.domain.account_info.AccountInfo
import com.yanschool.finapp.presentation.model.AccountBalanceUi
import java.math.BigDecimal
import javax.inject.Inject

class AccountInfoMapper @Inject constructor() {

    fun mapDomainToUi(domain: AccountInfo): AccountBalanceUi {
        return AccountBalanceUi(
            amount = BigDecimal(domain.balance).toStringWithCurrency(),
            currency = when (domain.currency) {
                RUBBLES -> "₽"
                else -> domain.currency
            },
        )
    }

    private companion object {
        const val RUBBLES = "RUB"
    }

}