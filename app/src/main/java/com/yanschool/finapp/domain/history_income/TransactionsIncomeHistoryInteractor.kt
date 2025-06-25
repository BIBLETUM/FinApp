package com.yanschool.finapp.domain.history_income

import com.yanschool.domain.common_models.TransactionDetail
import com.yanschool.finapp.domain.TransactionsRepository
import com.yanschool.finapp.domain.extensions.filterTransactionsByType
import com.yanschool.finapp.domain.extensions.sortDescendingByDate
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
) : ITransactionsIncomeHistoryInteractor {

    private val _startDate = MutableStateFlow<String?>(null)
    private val _endDate = MutableStateFlow<String?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getFlow(): Flow<Result<List<TransactionDetail>>> = combine(
        _startDate.filterNotNull(),
        _endDate.filterNotNull(),
    ) { start, end -> start to end }.flatMapLatest { (start, end) ->
        transactionsRepository.getTransactionsDetail(startDate = start, endDate = end)
            .map { result ->
                result
                    .filterTransactionsByType(true)
                    .sortDescendingByDate()
            }
    }.flowOn(Dispatchers.IO)


    override fun setStartDate(startDate: String) {
        _startDate.value = startDate
    }

    override fun setEndDate(endDate: String) {
        _endDate.value = endDate
    }

}

interface ITransactionsIncomeHistoryInteractor {

    fun getFlow(): Flow<Result<List<TransactionDetail>>>

    fun setStartDate(startDate: String)

    fun setEndDate(endDate: String)

}