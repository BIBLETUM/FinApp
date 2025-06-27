package com.yanschool.data.remote_data_source.common_mappers

import com.yanschool.data.remote_data_source.common_models.CategoryStatDto
import com.yanschool.domain.common_models.TransactionCategory
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
