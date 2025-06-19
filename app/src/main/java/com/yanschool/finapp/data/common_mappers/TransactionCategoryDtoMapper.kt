package com.yanschool.finapp.data.common_mappers

import com.yanschool.finapp.data.common_models.TransactionCategoryDto
import com.yanschool.finapp.domain.common_models.TransactionCategory
import javax.inject.Inject

class TransactionCategoryDtoMapper @Inject constructor() {
    fun mapDtoToDomain(dto: TransactionCategoryDto): TransactionCategory {
        return TransactionCategory(
            id = dto.id,
            name = dto.name,
            emoji = dto.emoji,
            isIncome = dto.isIncome,
        )
    }
}