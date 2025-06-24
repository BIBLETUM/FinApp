package com.yanschool.finapp.data.common_mappers

import com.yanschool.data.common_models.AccountInfoDto
import com.yanschool.finapp.domain.account_info.AccountInfo
import javax.inject.Inject

class AccountInfoMapper @Inject constructor() {

    fun mapDtoToDomain(dto: AccountInfoDto): AccountInfo {
        return AccountInfo(
            balance = dto.balance,
            currency = dto.currency,
        )
    }

}