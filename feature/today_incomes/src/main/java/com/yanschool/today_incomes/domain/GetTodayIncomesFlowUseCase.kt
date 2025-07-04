package com.yanschool.today_incomes.domain

import com.yanschool.domain.common_models.TransactionShort
import com.yanschool.domain.common_repository.TransactionsRepository
import com.yanschool.domain.common_usecase.IGetCurrentAccountFlowUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class GetTodayIncomesFlowUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository,
    private val accountIdFlowUseCase: IGetCurrentAccountFlowUseCase,
) : IGetTodayIncomesFlowUseCase {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun invoke(): Flow<Result<List<TransactionShort>>> {
        val todayString: String =
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().time)

        return accountIdFlowUseCase.invoke().flatMapLatest { account ->
            transactionsRepository.getTransactionsShort(
                accountId = account.id,
                startDate = todayString,
                endDate = todayString,
            ).map { result ->
                filterResultByIncome(result)
            }
        }
    }

    private fun filterResultByIncome(result: Result<List<TransactionShort>>): Result<List<TransactionShort>> {
        return result.map { list ->
            list.filter { it.category.isIncome }
        }
    }
}

/**
 * Интерфейс use case для получения потока кратких доходных транзакций за сегодняшний день.
 */
interface IGetTodayIncomesFlowUseCase {

    /**
     * Возвращает поток с результатом загрузки кратких доходных транзакций за текущий день.
     *
     * @return [Flow] с [Result], содержащим список транзакций или ошибку
     */
    operator fun invoke(): Flow<Result<List<TransactionShort>>>
}
