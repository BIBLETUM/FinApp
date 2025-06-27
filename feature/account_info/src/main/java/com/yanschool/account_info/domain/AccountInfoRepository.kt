package com.yanschool.account_info.domain

import kotlinx.coroutines.flow.Flow

/**
 * Репозиторий для получения информации об аккаунте.
 */
interface AccountInfoRepository {

    /**
     * Возвращает поток с информацией об аккаунте по его идентификатору.
     *
     * Поток эмитит [Result], содержащий объект [AccountInfo] при успешной загрузке
     * или ошибку в случае неудачи (например, проблемы с сетью или сервером).
     *
     * @param accountId идентификатор аккаунта, для которого запрашивается информация
     * @return [Flow] с результатом запроса информации об аккаунте
     */
    fun getAccountInfo(accountId: Int): Flow<Result<AccountInfo>>
}
