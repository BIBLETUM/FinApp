package com.yanschool.data.remote_data_source.api

import com.yanschool.data.remote_data_source.common_models.AccountInfoDto
import com.yanschool.data.remote_data_source.common_models.AccountSettingsDto
import com.yanschool.data.remote_data_source.common_models.AccountShortDto
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * Сервис для обновления информации о счете через сетевой API.
 */
interface AccountSettingsService {

    /**
     * Обновляет информацию о счете по его идентификатору.
     *
     * @param accountId уникальный идентификатор счета
     * @param [AccountInfoDto] DTO с данными счета
     * @return [AccountShortDto] DTO с обновленными данными счета
     */
    @PUT("accounts/{id}")
    suspend fun updateAccount(
        @Path("id") accountId: Int,
        @Body accountSettingsDto: AccountSettingsDto
    ): AccountShortDto
}
