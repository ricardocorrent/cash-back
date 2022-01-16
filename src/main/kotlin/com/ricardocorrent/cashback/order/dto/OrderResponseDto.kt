package com.ricardocorrent.cashback.order.dto

import com.ricardocorrent.cashback.order.OrderStatusEnum
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

open class OrderResponseDto(
    val id: UUID,
    val code: String,
    val value: BigDecimal,
    val date: LocalDateTime,
    val cashBackPercentage: BigDecimal,
    val cashBackValue: BigDecimal,
    val status: OrderStatusEnum,
)
