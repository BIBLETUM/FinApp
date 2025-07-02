package com.yanschool.common_mapper

import com.yanschool.common_models.AccountBalanceUi
import com.yanschool.domain.common_models.AccountInfo
import com.yanschool.utils.extensions.extractIntegerDigits
import com.yanschool.utils.extensions.getCurrencyName
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

    fun mapUiToDomain(ui: AccountBalanceUi): AccountInfo {
        val currencyName = ui.currencySymbol.getCurrencyName()

        return AccountInfo(
            id = ui.id,
            name = ui.name,
            balance = ui.amount.extractIntegerDigits(),
            currency = currencyName,
        )
    }
}
