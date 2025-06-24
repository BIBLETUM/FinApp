package com.yanschool.finapp.data.common_mappers

import com.yanschool.data.common_models.TransactionDto
import com.yanschool.domain.common_models.TransactionDetail
import com.yanschool.domain.common_models.TransactionShort
import javax.inject.Inject

class TransactionMapper @Inject constructor(
    private val transactionCategoryMapper: TransactionCategoryDtoMapper,
) {

    fun mapDtoToDomainShort(dto: TransactionDto): TransactionShort {
        return TransactionShort(
            id = dto.id,
            category = transactionCategoryMapper.mapDtoToDomain(dto.category),
            amount = dto.amount,
            comment = dto.comment,
        )
    }

    fun mapDtoToDomainDetail(dto: TransactionDto): TransactionDetail {
        return TransactionDetail(
            id = dto.id,
            category = transactionCategoryMapper.mapDtoToDomain(dto.category),
            amount = dto.amount,
            comment = dto.comment,
            dateTime = dto.transactionDate,
        )
    }

}