package com.yanschool.finapp.data.common_mappers

import com.yanschool.finapp.data.common_models.TransactionDto
import com.yanschool.finapp.domain.common_models.TransactionShort
import javax.inject.Inject

class TransactionMapper @Inject constructor(
    private val transactionCategoryMapper: TransactionCategoryDtoMapper,
) {

    fun mapDtoToDomain(dto: TransactionDto): TransactionShort {
        return TransactionShort(
            id = dto.id,
            category = transactionCategoryMapper.mapDtoToDomain(dto.category),
            amount = dto.amount,
            comment = dto.comment,
        )
    }

}