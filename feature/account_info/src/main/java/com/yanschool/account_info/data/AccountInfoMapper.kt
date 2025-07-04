package com.yanschool.account_info.data

import com.yanschool.data.remote_data_source.common_models.AccountInfoDto
import com.yanschool.domain.common_models.AccountInfo
import javax.inject.Inject

class AccountInfoMapper @Inject constructor() {

    fun mapDtoToDomain(dto: AccountInfoDto): AccountInfo {
        return AccountInfo(
            id = dto.id,
            name = dto.name,
            balance = dto.balance,
            currency = dto.currency,
        )
    }
}
