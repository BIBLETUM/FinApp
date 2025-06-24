package com.yanschool.utils.extensions

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Long.formatTimestampToDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        .withZone(ZoneId.systemDefault())

    return formatter.format(Instant.ofEpochMilli(this))
}