package com.yanschool.data.remote_data_source.api

import com.yanschool.data.remote_data_source.common_models.AccountShortDto
import retrofit2.http.GET

/**
 * Сервис для получения списка счетов через сетевой API.
 */
interface AccountsService {

    /**
     * Запрашивает список счетов пользователя.
     *
     * @return список DTO пользователей ([AccountShortDto])
     */
    @GET("accounts")
    suspend fun getAccounts(): List<AccountShortDto>
}
