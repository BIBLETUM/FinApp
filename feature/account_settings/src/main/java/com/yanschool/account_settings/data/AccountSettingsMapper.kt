package com.yanschool.account_settings.data

import com.yanschool.data.remote_data_source.common_models.AccountSettingsDto
import com.yanschool.domain.common_models.AccountInfo
import javax.inject.Inject

class AccountSettingsMapper @Inject constructor() {

    fun mapDomainToDto(account: AccountInfo): AccountSettingsDto {
        return AccountSettingsDto(
            name = account.name,
            balance = account.balance,
            currency = account.currency,
        )
    }
}
