package com.yanschool.data.remote_data_source.api

import com.yanschool.data.remote_data_source.common_models.AccountInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Сервис для получения информации о счете через сетевой API.
 */
interface AccountInfoService {

    /**
     * Запрашивает информацию о счете по его идентификатору.
     *
     * @param accountId уникальный идентификатор счета
     * @return [AccountInfoDto] DTO с данными счета
     */
    @GET("accounts/{id}")
    suspend fun getAccountInfo(@Path("id") accountId: Int): AccountInfoDto
}
