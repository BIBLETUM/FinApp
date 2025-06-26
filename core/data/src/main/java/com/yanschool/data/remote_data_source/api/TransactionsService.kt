package com.yanschool.data.remote_data_source.api

import com.yanschool.data.remote_data_source.common_models.TransactionDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Сервис для получения списка транзакций через сетевой API.
 */
interface TransactionsService {

    /**
     * Запрашивает список транзакций для указанного счета за определённый период.
     *
     * @param accountId идентификатор счета
     * @param startDate начальная дата периода в формате "yyyy-MM-dd", необязательный параметр
     * @param endDate конечная дата периода в формате "yyyy-MM-dd", необязательный параметр
     * @return список DTO транзакций ([TransactionDto])
     */
    @GET("transactions/account/{id}/period")
    suspend fun getTransactions(
        @Path("id") accountId: Int,
        @Query("startDate") startDate: String? = null,
        @Query("endDate") endDate: String? = null
    ): List<TransactionDto>
}
