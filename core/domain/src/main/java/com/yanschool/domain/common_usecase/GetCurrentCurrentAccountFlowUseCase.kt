package com.yanschool.domain.common_usecase

import com.yanschool.domain.common_models.AccountInfo
import com.yanschool.domain.common_repository.CurrentAccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCurrentCurrentAccountFlowUseCase @Inject constructor(
    private val currentAccountRepository: CurrentAccountRepository,
) : IGetCurrentAccountFlowUseCase {

    override fun invoke(): Flow<AccountInfo?> {
        return currentAccountRepository.getCurrentAccountFlow()
    }
}

/**
 * Интерфейс use case для предоставления потока текущего аккаунта.
 */
interface IGetCurrentAccountFlowUseCase {

    /**
     * Возвращает поток, отслеживающий текущий аккаунт.
     *
     * @return [Flow], содержащий значение [AccountInfo] или `null`, если аккаунт ещё не установлен
     */
    operator fun invoke(): Flow<AccountInfo?>
}
