package com.yanschool.account_settings.domain

import com.yanschool.domain.common_models.AccountInfo
import kotlinx.coroutines.flow.Flow

/**
 * Репозиторий для управления настройками аккаунта.
 */
interface AccountSettingsRepository {

    /**
     * Обновляет данные аккаунта.
     *
     * @param account Обновлённая информация об аккаунте.
     * @return [Result] с [Flow], который эмитит актуальные данные аккаунта после обновления.
     * В случае ошибки содержит описание проблемы.
     */
    fun updateAccount(
        account: AccountInfo
    ): Flow<Result<AccountInfo>>
}
