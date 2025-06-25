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
                        transactionsRepository.getTransactionsDetail(
                            accountId = accountId,
                            startDate = start,
                            endDate = end
                        ).map { result ->
                            result
                                .filterTransactionsByType(true)
                                .sortDescendingByDate()
                        }
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

    private fun dateRangeFlow(): Flow<Pair<String, String>> {
        return combine(
            _startDate.filterNotNull(),
            _endDate.filterNotNull()
        ) { start, end -> start to end }
    }

}

interface ITransactionsIncomeHistoryInteractor {

    fun getFlow(): Flow<Result<List<TransactionDetail>>>

    fun setStartDate(startDate: String)

    fun setEndDate(endDate: String)

}