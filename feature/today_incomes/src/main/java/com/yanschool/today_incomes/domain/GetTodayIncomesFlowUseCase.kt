package com.yanschool.today_incomes.domain

import com.yanschool.domain.common_models.TransactionShort
import com.yanschool.domain.common_repository.TransactionsRepository
import com.yanschool.domain.common_usecase.IGetAccountIdFlowUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class GetTodayIncomesFlowUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository,
    private val accountIdFlowUseCase: IGetAccountIdFlowUseCase,
) : IGetTodayIncomesFlowUseCase {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun invoke(): Flow<Result<List<TransactionShort>>> {
        val todayString: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            .format(Calendar.getInstance().time)

        return accountIdFlowUseCase.invoke()
            .filterNotNull()
            .flatMapLatest { accountId ->
                transactionsRepository.getTransactionsShort(
                    accountId = accountId,
                    startDate = todayString,
                    endDate = todayString,
                ).map { result ->
                    result.map { list ->
                        list.filter { it.category.isIncome }
                    }
                }
            }
    }
}

interface IGetTodayIncomesFlowUseCase {
    operator fun invoke(): Flow<Result<List<TransactionShort>>>
}