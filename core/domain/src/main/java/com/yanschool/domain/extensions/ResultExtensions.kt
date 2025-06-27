package com.yanschool.domain.extensions

import com.yanschool.domain.common_models.TransactionDetail
import java.time.Instant

/**
 * Сортирует список транзакций по убыванию даты и времени.
 *
 * Предполагается, что поле [TransactionDetail.dateTime] содержит строку в формате ISO-8601,
 * которая может быть корректно распознана через [Instant.parse].
 *
 * @return [Result] с отсортированным по убыванию списком транзакций,
 *         либо исходная ошибка, если [Result] содержит её
 */
fun Result<List<TransactionDetail>>.sortDescendingByDate(): Result<List<TransactionDetail>> {
    return this.map { list ->
        list.sortedByDescending { transaction ->
            Instant.parse(transaction.dateTime)
        }
    }
}

/**
 * Фильтрует список транзакций по признаку дохода или расхода.
 *
 * @param isIncome если `true`, будут оставлены только транзакции с категорией-доходом;
 *                 если `false` — только расходы
 *
 * @return [Result] с отфильтрованным списком транзакций,
 *         либо исходная ошибка, если [Result] содержит её
 */
fun Result<List<TransactionDetail>>.filterTransactionsByType(isIncome: Boolean): Result<List<TransactionDetail>> {
    return this.map { list ->
        list.filter {
            it.category.isIncome == isIncome
        }
    }
}
