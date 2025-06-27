package com.yanschool.domain.common_repository

import com.yanschool.domain.common_models.TransactionDetail
import com.yanschool.domain.common_models.TransactionShort
import kotlinx.coroutines.flow.Flow

interface TransactionsRepository {

    /**
     * Возвращает поток коротких данных по транзакциям для указанного счёта.
     *
     * @param accountId идентификатор счёта, по которому запрашиваются транзакции
     * @param startDate дата начала выборки транзакций в формате ISO-8601 (например, "2025-01-01");
     *                  Если не указана, используется начало текущего месяца.
     * @param endDate   дата конца выборки транзакций в формате ISO-8601 (например, "2025-01-31");
     *                  Если не указана, используется конец текущего месяца.
     * @return [Flow] с [Result], содержащим список объектов [TransactionShort] при успешном выполнении
     *         или ошибку в случае неудачи
     */
    fun getTransactionsShort(
        accountId: Int,
        startDate: String? = null,
        endDate: String? = null,
    ): Flow<Result<List<TransactionShort>>>

    /**
     * Возвращает поток детализированных данных по транзакциям для указанного счёта.
     *
     * @param accountId идентификатор счёта, по которому запрашиваются транзакции
     * @param startDate дата начала выборки транзакций в формате ISO-8601 (например, "2025-01-01");
     *                  Если не указана, используется начало текущего месяца.
     * @param endDate   дата конца выборки транзакций в формате ISO-8601 (например, "2025-01-31");
     *                  Если не указана, используется конец текущего месяца.
     * @return [Flow] с [Result], содержащим список объектов [TransactionDetail] при успешном выполнении
     *         или ошибку в случае неудачи
     */
    fun getTransactionsDetail(
        accountId: Int,
        startDate: String? = null,
        endDate: String? = null,
    ): Flow<Result<List<TransactionDetail>>>
}
