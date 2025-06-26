package com.yanschool.data.remote_data_source.common_mappers

import com.yanschool.data.remote_data_source.common_models.TransactionDto
import com.yanschool.domain.common_models.TransactionDetail
import javax.inject.Inject

class TransactionDetailDtoMapper @Inject constructor(
    private val transactionCategoryDtoMapper: TransactionCategoryDtoMapper,
) {

    fun mapDtoToDomain(dto: TransactionDto): TransactionDetail {
        return TransactionDetail(
            id = dto.id,
            category = transactionCategoryDtoMapper.mapDtoToDomain(dto.category),
            amount = dto.amount,
            comment = dto.comment,
            dateTime = dto.transactionDate,
        )
    }
}
