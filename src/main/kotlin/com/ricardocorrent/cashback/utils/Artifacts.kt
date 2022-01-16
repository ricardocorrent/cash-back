package com.ricardocorrent.cashback.utils

import com.ricardocorrent.cashback.order.OrderStatusEnum
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

fun String.justNumbers() = this.partition { it.isDigit() }.first

fun BigDecimal.formatDecimal(): BigDecimal = this.multiply(BigDecimal.valueOf(100))

fun BigDecimal.scaled(): BigDecimal = this.setScale(2, RoundingMode.HALF_UP)

infix fun <T> T.isEqTo(other: T): Boolean {
    return this == other
}

val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
val randomString: String
    get() = (1..20)
        .map { charset.random() }
        .joinToString(separator = "")

val randomNumbersAsString: String
    get() = (0 until 10)
        .map { (0..9).random() }
        .joinToString(separator = "")

val randomUUIDs: UUID
    get() = UUID.randomUUID()

val randomBoolean: Boolean
    get() = listOf(true, false).random()

fun randomValueUntil(maxValue: BigDecimal) =
    generateRandomBigDecimalFromRange(BigDecimal.valueOf(0.0), maxValue)

val randomBigDecimalValue: BigDecimal
    get() = generateRandomBigDecimalFromRange(BigDecimal.valueOf(0.0), BigDecimal.valueOf(1000.00))

fun generateRandomBigDecimalFromRange(min: BigDecimal, max: BigDecimal): BigDecimal {
    return min.add(BigDecimal(Math.random())).multiply(max.subtract(min)).scaled()
}

val randomOrderStatus: OrderStatusEnum
    get() = OrderStatusEnum.values().random()
