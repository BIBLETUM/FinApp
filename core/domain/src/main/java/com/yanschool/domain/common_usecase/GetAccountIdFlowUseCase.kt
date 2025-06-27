package com.yanschool.domain.common_usecase

import com.yanschool.domain.common_repository.AccountIdRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAccountIdFlowUseCase @Inject constructor(
    private val accountIdRepository: AccountIdRepository,
) : IGetAccountIdFlowUseCase {

    override fun invoke(): Flow<Int?> {
        return accountIdRepository.getCurrentAccountIdFlow()
    }
}

/**
 * Интерфейс use case для предоставления потока текущего идентификатора аккаунта.
 */
interface IGetAccountIdFlowUseCase {

    /**
     * Возвращает поток, отслеживающий идентификатор текущего аккаунта.
     *
     * @return [Flow], содержащий значение [Int] или `null`, если идентификатор ещё не установлен
     */
    operator fun invoke(): Flow<Int?>
}
