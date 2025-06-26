package com.yanschool.account_info.data

import com.yanschool.account_info.domain.AccountInfo
import com.yanschool.data.remote_data_source.common_models.AccountInfoDto
import javax.inject.Inject

class AccountInfoMapper @Inject constructor() {

    fun mapDtoToDomain(dto: AccountInfoDto): AccountInfo {
        return AccountInfo(
            balance = dto.balance,
            currency = dto.currency,
        )
    }
}
