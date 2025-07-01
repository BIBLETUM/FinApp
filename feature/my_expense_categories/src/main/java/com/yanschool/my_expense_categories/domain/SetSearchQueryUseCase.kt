package com.yanschool.my_expense_categories.domain

import javax.inject.Inject

class SetSearchQueryUseCase @Inject constructor(
    private val innerSearchFlow: InnerSearchFlow
) : ISetSearchQueryUseCase {

    override fun invoke(query: String) {
        innerSearchFlow.setSearchQuery(query)
    }
}

/**
 * Интерфейс use case для установки поискового запроса для транзакций.
 */
interface ISetSearchQueryUseCase {
    operator fun invoke(query: String)
}
