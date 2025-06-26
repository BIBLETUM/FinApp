package com.yanschool.utils.extensions

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Преобразует значение [Long], представляющее собой метку времени (timestamp) в миллисекундах,
 * в строку формата "yyyy-MM-dd" с учётом системной временной зоны.
 *
 * Пример:
 * ```
 * 1672531199000L.formatTimestampToDate() // "2022-12-31" (в зависимости от временной зоны)
 * ```
 *
 * @return строка с датой в формате "год-месяц-день"
 */
fun Long.formatTimestampToDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        .withZone(ZoneId.systemDefault())

    return formatter.format(Instant.ofEpochMilli(this))
}