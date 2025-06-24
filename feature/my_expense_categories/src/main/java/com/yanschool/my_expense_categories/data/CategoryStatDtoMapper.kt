package com.yanschool.my_expense_categories.data

import com.yanschool.data.common_models.CategoryStatDto
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