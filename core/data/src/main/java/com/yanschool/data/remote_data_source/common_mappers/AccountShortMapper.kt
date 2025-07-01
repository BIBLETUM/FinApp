package com.yanschool.data.remote_data_source.common_mappers

import com.yanschool.data.remote_data_source.common_models.AccountShortDto
import com.yanschool.domain.common_models.AccountInfo
import javax.inject.Inject

class AccountShortMapper @Inject constructor() {

    fun mapDtoToDomain(dto: AccountShortDto): AccountInfo {
        return AccountInfo(
            id = dto.id,
            name = dto.name,
            balance = dto.balance,
            currency = dto.currency,
        )
    }
}