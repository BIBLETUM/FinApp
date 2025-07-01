package com.yanschool.domain.common_repository

import com.yanschool.domain.common_models.AccountInfo
import kotlinx.coroutines.flow.StateFlow

/**
 * Репозиторий для загрузки и предоставления текущего аккаунта.
 */
interface CurrentAccountRepository {

    /**
     * Инициирует загрузку аккаунта.
     */
    suspend fun loadAccount()

    /**
     * Возвращает поток, отслеживающий текущий аккаунт.
     *
     * Поток выдаёт:
     * - `null`, пока аккаунт не будет загружен или при отсутствии аккаунта;
     * - значение [AccountInfo], когда аккаунт успешно загружен.
     *
     * @return [StateFlow] с текущим аккаунтом или `null`
     */
    fun getCurrentAccountFlow(): StateFlow<AccountInfo?>

    /**
     * Устанавливает новое значение аккаунта для всего приложения.
     *
     * @param [AccountInfo] новое значение аккаунта
     */
    fun setNewAccountInfo(accountInfo: AccountInfo)
}
