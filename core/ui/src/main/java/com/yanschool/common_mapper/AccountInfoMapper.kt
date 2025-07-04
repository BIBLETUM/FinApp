package com.yanschool.common_mapper

import com.yanschool.common_models.AccountBalanceUi
import com.yanschool.domain.common_models.AccountInfo
import com.yanschool.utils.extensions.getCurrencySymbol
import com.yanschool.utils.extensions.toStringWithCurrency
import java.math.BigDecimal
import javax.inject.Inject

class AccountInfoMapper @Inject constructor() {

    fun mapDomainToUi(domain: AccountInfo): AccountBalanceUi {
        val currencySymbol = domain.currency.getCurrencySymbol()

        return AccountBalanceUi(
            id = domain.id,
            name = domain.name,
            amount = BigDecimal(domain.balance).toStringWithCurrency(
                currencySymbol
            ),
            currencySymbol = currencySymbol,
        )
    }
}
