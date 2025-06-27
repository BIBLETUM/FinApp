package com.yanschool.history_incomes.domain

import com.yanschool.domain.common_models.TransactionDetail
import com.yanschool.domain.common_repository.TransactionsRepository
import com.yanschool.domain.common_usecase.IGetAccountIdFlowUseCase
import com.yanschool.domain.extensions.filterTransactionsByType
import com.yanschool.domain.extensions.sortDescendingByDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionsIncomeHistoryInteractor @Inject constructor(
    private val transactionsRepository: TransactionsRepository,
    private val accountIdFlowUseCase: IGetAccountIdFlowUseCase
) : ITransactionsIncomeHistoryInteractor {

    private val _startDate = MutableStateFlow<String?>(null)
    private val _endDate = MutableStateFlow<String?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getFlow(): Flow<Result<List<TransactionDetail>>> {
        return accountIdFlowUseCase.invoke()
            .filterNotNull()
            .flatMapLatest { accountId ->
                dateRangeFlow()
                    .flatMapLatest { (start, end) ->
                        getTransactionsDetailFlow(accountId, start, end)
                    }
            }
            .flowOn(Dispatchers.IO)
    }

    override fun setStartDate(startDate: String) {
        _startDate.value = startDate
    }

    override fun setEndDate(endDate: String) {
        _endDate.value = endDate
    }

    private fun getTransactionsDetailFlow(
        accountId: Int,
        startDate: String,
        endDate: String
    ): Flow<Result<List<TransactionDetail>>> {
        return transactionsRepository.getTransactionsDetail(
            accountId = accountId,
            startDate = startDate,
            endDate = endDate,
        ).map { result ->
            result
                .filterTransactionsByType(true)
                .sortDescendingByDate()
        }
    }

    private fun dateRangeFlow(): Flow<Pair<String, String>> {
        return combine(
            _startDate.filterNotNull(),
            _endDate.filterNotNull()
        ) { start, end -> start to end }
    }
}

/**
 * Интерфейс интерактора для получения истории доходных транзакций.
 */
interface ITransactionsIncomeHistoryInteractor {

    /**
     * Возвращает поток результатов с детализированными доходными транзакциями
     * за указанный период и текущий аккаунт.
     *
     * @return [Flow] с [Result], содержащим список транзакций или ошибку
     */
    fun getFlow(): Flow<Result<List<TransactionDetail>>>

    /**
     * Устанавливает начальную дату периода фильтрации транзакций.
     *
     * @param startDate дата начала периода в формате ISO-8601 (например, "2025-01-01")
     */
    fun setStartDate(startDate: String)

    /**
     * Устанавливает конечную дату периода фильтрации транзакций.
     *
     * @param endDate дата конца периода в формате ISO-8601 (например, "2025-01-31")
     */
    fun setEndDate(endDate: String)
}
