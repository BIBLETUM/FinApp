package com.yanschool.account_info.domain

import com.yanschool.domain.common_usecase.IGetAccountIdFlowUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class GetAccountInfoFlowUseCase @Inject constructor(
    private val repository: AccountInfoRepository,
    private val getAccountIdFlowUseCase: IGetAccountIdFlowUseCase,
) : IGetAccountInfoFlowUseCase {

    @OptIn(ExperimentalCoroutinesApi::class)
    override operator fun invoke(): Flow<Result<AccountInfo>> {
        return getAccountIdFlowUseCase.invoke()
            .filterNotNull()
            .flatMapLatest { accountId ->
                repository.getAccountInfo(accountId)
            }
    }
}

/**
 * Интерфейс use case для предоставления потока с информацией об аккаунте.
 */
interface IGetAccountInfoFlowUseCase {

    /**
     * Возвращает поток, содержащий результат загрузки информации об аккаунте
     * на основе текущего активного идентификатора аккаунта.
     *
     * @return [Flow] с [Result], содержащим [AccountInfo] или ошибку
     */
    operator fun invoke(): Flow<Result<AccountInfo>>

}