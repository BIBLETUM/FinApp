package com.yanschool.common_screen_layout.history

import com.yanschool.common_models.TransactionDetailUi

interface HistoryScreenStateContent {
    val startDate: String
    val endDate: String
    val total: String
    val transactions: List<TransactionDetailUi>
}