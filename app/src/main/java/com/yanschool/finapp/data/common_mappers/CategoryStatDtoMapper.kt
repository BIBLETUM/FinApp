package com.yanschool.finapp.data.common_mappers

import com.yanschool.finapp.data.common_models.CategoryStatDto
import com.yanschool.finapp.domain.common_models.TransactionCategory
import javax.inject.Inject

class CategoryStatDtoMapper @Inject constructor() {
    fun mapDtoToDomain(dto: CategoryStatDto, isIncome: Boolean): TransactionCategory {
        return TransactionCategory(
            id = dto.categoryId,
            name = dto.categoryName,
            emoji = dto.emoji,
            isIncome = isIncome,
        )
    }
}