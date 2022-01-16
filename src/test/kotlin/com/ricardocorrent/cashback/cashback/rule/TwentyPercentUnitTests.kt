package com.ricardocorrent.cashback.cashback.rule

import com.ricardocorrent.cashback.utils.randomBigDecimalValue
import com.ricardocorrent.cashback.utils.scaled
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class TwentyPercentUnitTests {

    @Test
    fun `it should return null when asked for the next cash back`() {
        // scenario
        val expectedNext = null

        // execution
        val response = TwentyPercent.next

        // validation
        assertTrue { expectedNext == response }
    }

    @Test
    fun `it should return expected percentage when asked to get percentage used to calculate cash back`() {
        // scenario
        val expectedPercentage = BigDecimal.valueOf(0.20)

        // execution
        val response = TwentyPercent.percentage

        // validation
        assertTrue { expectedPercentage == response }
    }

    @Test
    fun `it should calculate cash back when asked for do it`() {
        // scenario
        val value = randomBigDecimalValue
        val percentage = BigDecimal.valueOf(0.20)
        val expectedCashBack = value.multiply(percentage).scaled()

        // execution
        val (_, cashBack) = TwentyPercent.calculateCashBackValue(value)

        // validation
        assertTrue { expectedCashBack == cashBack }
    }

}
